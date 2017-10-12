package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.MarketDao;
import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.exception.ServiceException;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class MarketService extends AbstractService {
    private static MarketService instance;

    public static MarketService getInstance() {
        if (instance == null) {
            synchronized (MarketService.class) {
                if (instance == null) {
                    instance = new MarketService();
                }
            }
        }
        return instance;
    }

    private MarketService() {
    }

    public Market add(Market market) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                MarketDao marketDao = daoFactory.getMarketDao(connection);
                market = marketDao.add(market);
                Collection<Outcome> outcomes = market.getOutcomes();
                if (outcomes != null && outcomes.size() > 0) {
                    OutcomeService outcomeService = ServiceFactory.getInstance().getOutcomeService();
                    for (Outcome outcome : market.getOutcomes()) {
                        outcome.setMarketId(market.getId());
                        outcomeService.add(outcome);
                    }
                }
                return market;
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}
