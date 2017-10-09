package com.kukuruznyak.bettingcompany.entity.bet.builder;

import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.ResultOfBet;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.math.BigDecimal;
import java.util.Date;

public class BetBuilder {
    private Long id = null;
    private User client;
    private Date date = new Date();
    private BigDecimal sumIn;
    private BigDecimal sumOut;
    private TypeOfBet type;
    private ResultOfBet result = ResultOfBet.UNKNOWN;
    private double totalCoefficient;
    private String description;

    public BetBuilder() {
    }

    public BetBuilder(User client, BigDecimal sumIn) {
        this.client = client;
        this.sumIn = sumIn;
    }

    public BetBuilder buildId(Long id) {
        this.id = id;
        return this;
    }

    public BetBuilder buildClient(User client) {
        this.client = client;
        return this;
    }

    public BetBuilder buildDate(Date date) {
        this.date = date;
        return this;
    }

    public BetBuilder buildSumIn(BigDecimal sumIn) {
        this.sumIn = sumIn;
        return this;
    }

    public BetBuilder buildSumOut(BigDecimal sumOut) {
        this.sumOut = sumOut;
        return this;
    }

    public BetBuilder buildType(TypeOfBet type) {
        this.type = type;
        return this;
    }

    public BetBuilder buildResult(ResultOfBet result) {
        this.result = result;
        return this;
    }

    public BetBuilder buildTotalCoefficient(double totalCoefficient) {
        this.totalCoefficient = totalCoefficient;
        return this;
    }

    public BetBuilder buildDescription(String description) {
        this.description = description;
        return this;
    }

    public Bet build() {
        Bet bet = new Bet();
        bet.setId(this.id);
        bet.setClient(this.client);
        bet.setDate(this.date);
        bet.setSumIn(this.sumIn);
        bet.setSumOut(this.sumOut);
        bet.setType(this.type);
        bet.setResult(this.result);
        bet.setTotalCoefficient(this.totalCoefficient);
        bet.setDescription(this.description);
        return bet;
    }
}
