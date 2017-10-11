package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.dao.BetItemDao;
import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.BetItem;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.exception.ServiceException;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BetService extends AbstractService {
    private static BetService instance;

    public static BetService getInstance() {
        if (instance == null) {
            synchronized (BetService.class) {
                if (instance == null) {
                    instance = new BetService();
                }
            }
        }
        return instance;
    }

    private BetService() {
    }

    public boolean placeBet(Bet bet) {
        try {
            Connection connection = dataSource.getConnection();
            try {
                connection.setAutoCommit(false);

                BigDecimal sum = bet.getSumIn();

                ClientDao clientDao = daoFactory.getClientDao(connection);
                Client client = clientDao.getById(bet.getClientId());
                if (client.getBalance().compareTo(sum) >= 0) {
                    client.setBalance(client.getBalance().subtract(sum));
                    clientDao.update(client);
                } else {
                    throw new ServiceException(StringMessages.getMessage(StringMessages.NOT_ENOUGH_MONEY));
                }

                BetDao betDao = daoFactory.getBetDao(connection);
                bet = betDao.add(bet);
                BetItemDao betItemDao = daoFactory.getBetItemDao(connection);
                for (BetItem betItem : bet.getItems()) {
                    betItem.setBetId(bet.getId());
                    betItem.setId(betItemDao.add(betItem).getId());
                }

                EventDao eventDao = daoFactory.getEventDao(connection);
                BigDecimal portion = sum.divide(new BigDecimal(bet.getItems().size()));
                for (BetItem betItem : bet.getItems()) {
                    Event event = eventDao.getByBetItemId(betItem.getId());
                    if (event != null) {
                        event.setTurnover(event.getTurnover().add(portion));
                        eventDao.update(event);
                    } else {
                        throw new ServiceException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
                    }
                }

                connection.commit();
                return true;
            } catch (SQLException | ServiceException e) {
                connection.rollback();
                throw new ServiceException(e);
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void update(Bet bet) {
        try {
            Connection connection = dataSource.getConnection();
            try {
                connection.setAutoCommit(false);
                BetDao betDao = daoFactory.getBetDao(connection);
                betDao.update(bet);
                BetItemDao betItemDao = daoFactory.getBetItemDao(connection);
                for (BetItem betItem : bet.getItems()) {
                    betItem.setBetId(bet.getId());
                    betItemDao.update(betItem);
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Collection<Bet> getBetsByClientId(Long clientId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                BetDao betDao = daoFactory.getBetDao(connection);
                Collection<Bet> bets = betDao.getByClientId(clientId);
                BetItemDao betItemDao = daoFactory.getBetItemDao(connection);
                for (Bet bet : bets) {
                    bet.setItems(betItemDao.getAllByBetId(bet.getId()));
                }
                return bets;
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Collection<Bet> getBetsByEvent(Event event) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                Collection<Bet> betsByEvent = new HashSet<>();
                BetDao betDao = daoFactory.getBetDao(connection);
                for (Market market : event.getMarkets()) {
                    for (Outcome outcome : market.getOutcomes()) {
                        betsByEvent.addAll(betDao.getByOutcomeId(outcome.getId()));
                    }
                }
                return betsByEvent;
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void calculateBets(Event event) {//TODO
    }

    public Bet writeOutcomesIntoBet(Bet bet, Set<Outcome> collectedOutcomes) {
        for (Outcome outcome : collectedOutcomes) {
            bet.addItems(new BetItem(outcome));
        }
        bet.setType(identifyTypeOfBet(bet));
        bet.setTotalCoefficient(calculateTotalCoefficient(bet));
        return bet;
    }

    private TypeOfBet identifyTypeOfBet(Bet bet) {
        return bet.getItems().size() == 1 ? TypeOfBet.SINGLE : TypeOfBet.EXPRESS;
    }

    private double calculateTotalCoefficient(Bet bet) {
        double totalCoefficient = 1;
        for (BetItem betItem : bet.getItems()) {
            totalCoefficient *= betItem.getCoefficient();
        }
        return totalCoefficient;
    }
}
