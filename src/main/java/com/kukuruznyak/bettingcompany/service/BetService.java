package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.ResultOfBet;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public List<Bet> getBetByUser(Long clientId) {
        Bet bet1 = new Bet();
        bet1.setType(TypeOfBet.SINGLE);
        bet1.setTotalCoefficient(2);
        bet1.setSumIn(new BigDecimal("2"));
        bet1.setSumOut(new BigDecimal("4"));
        bet1.setResult(ResultOfBet.WIN);
        Outcome outcome1 = new Outcome();
        outcome1.setName("Black horse");
        outcome1.setCoefficient(2);
        bet1.addOutcome(outcome1);

        Bet bet2 = new Bet();
        bet2.setType(TypeOfBet.EXPRESS);
        bet2.setTotalCoefficient(4);
        bet2.setSumIn(new BigDecimal("2"));
        bet2.setSumOut(new BigDecimal("4"));
        bet2.setResult(ResultOfBet.WIN);
        bet2.addOutcome(outcome1);
        bet2.addOutcome(outcome1);
        List<Bet> bets = new ArrayList<>();
        bets.add(bet1);
        bets.add(bet2);
        return bets;
    }
}
