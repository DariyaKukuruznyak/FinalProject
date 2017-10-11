package com.kukuruznyak.bettingcompany.entity.bet;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;

public class BetItem extends Model {
    private String name;
    private double coefficient;
    private Long outcomeId;
    private Long betId;
    private ResultOfBet result;

    public BetItem() {
    }

    public BetItem(Outcome outcome) {
        this.name = outcome.getName();
        this.coefficient = outcome.getCoefficient();
        this.outcomeId = outcome.getId();
        this.result = ResultOfBet.UNKNOWN;
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

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public ResultOfBet getResult() {
        return result;
    }

    public void setResult(ResultOfBet result) {
        this.result = result;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BetItem)) return false;

        BetItem betItem = (BetItem) o;

        if (Double.compare(betItem.getCoefficient(), getCoefficient()) != 0) return false;
        if (getName() != null ? !getName().equals(betItem.getName()) : betItem.getName() != null) return false;
        return getOutcomeId() != null ? getOutcomeId().equals(betItem.getOutcomeId()) : betItem.getOutcomeId() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        temp = Double.doubleToLongBits(getCoefficient());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getOutcomeId() != null ? getOutcomeId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BetItem{" +
                "name='" + name + '\'' +
                ", coefficient=" + coefficient +
                ", outcomeId=" + outcomeId +
                '}';
    }
}
