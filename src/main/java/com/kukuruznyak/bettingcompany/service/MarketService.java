package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.entity.bet.Bet;

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

    public  boolean makeBet(Bet bet) {
        return true;
    }
}
