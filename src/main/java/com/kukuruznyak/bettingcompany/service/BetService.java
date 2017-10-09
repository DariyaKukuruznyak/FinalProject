package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.BetItem;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class BetService extends AbstractService {
    private static BetService instance;
    private BetDao betDao = daoFactory.getBetDao();

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

    public Collection<Bet> getBetByUser(Long clientId) {
        return betDao.getByUserId(clientId);
    }

    public void calculateBets(Event event) {//TODO
    }

    public Collection<Bet> getAll() {
        return betDao.getAll();
    }

    public void add(Bet bet) {
        betDao.add(bet);
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
