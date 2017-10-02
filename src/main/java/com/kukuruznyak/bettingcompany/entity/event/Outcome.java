package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Outcome extends Model {
    private String name;
    private double coefficient;
    private Market market;
    private List<Bet> bets;

    public Outcome() {
    }

    public Outcome(Long id) {
        super(id);
    }

    public Outcome(String name) {
        this.name = name;
    }

    public Outcome(String name, double coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void addBet(Bet bet) {
        if(this.bets==null){
            this.bets=new LinkedList<>();
        }
        this.bets.add(bet);
    }

    public String getMarket() {
        //TODO
        return "I am Market Name";
//        return this.market.getEvent().getTournament().toString();
    }

    @Override
    public String toString() {
        return name + ": " + coefficient;
    }
}
