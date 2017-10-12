package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;

import java.util.LinkedList;
import java.util.List;

public class Outcome extends Model implements Comparable<Object> {
    private String name;
    private double coefficient;
    private Long marketId;
    private List<Bet> bets = new LinkedList<>();

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

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void addBet(Bet bet) {
        this.bets.add(bet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Outcome)) return false;

        Outcome outcome = (Outcome) o;

        if (Double.compare(outcome.getCoefficient(), getCoefficient()) != 0) return false;
        if (getName() != null ? !getName().equals(outcome.getName()) : outcome.getName() != null) return false;
        if (getMarketId() != null ? !getMarketId().equals(outcome.getMarketId()) : outcome.getMarketId() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return name + ": " + coefficient;
    }

    @Override
    public int compareTo(Object object) {
        Outcome outcome = (Outcome) object;
        return this.id > outcome.getId() ? 1 : this.id.equals(outcome.getId()) ? 0 : -1;
    }
}
