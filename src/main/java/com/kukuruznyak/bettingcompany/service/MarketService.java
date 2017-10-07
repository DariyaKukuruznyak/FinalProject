package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.MarketDao;

public class MarketService extends AbstractService {
    private static MarketService instance;
    private MarketDao marketDao = daoFactory.getMarketDao();

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
}
