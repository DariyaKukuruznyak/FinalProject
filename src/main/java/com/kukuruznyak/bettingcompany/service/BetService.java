package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.ResultOfBet;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public List<Bet> getBetByUser(Long clientId) {return null;
    }

    public void calculateBets(Event event) {
    }

    public Collection<Bet> getAll() {
        return betDao.getAll();
    }
}
