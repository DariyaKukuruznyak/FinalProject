package com.kukuruznyak.bettingcompany.entity.event.market;

import com.kukuruznyak.bettingcompany.entity.enums.OutcomeNames;

public class TwoOutcomesMarketItem extends MarketItem {
    private Outcome outcome1;
    private Outcome outcome2;

    public void update(double coefficient, int outcomeNumber) {

    }

    public Outcome getOutcome1() {
        return outcome1;
    }

    public void setOutcome1(Outcome outcome1) {
        this.outcome1 = outcome1;
    }

    public Outcome getOutcome2() {
        return outcome2;
    }

    public void setOutcome2(Outcome outcome2) {
        this.outcome2 = outcome2;
    }

    public void updateCoefficient(OutcomeNames outcomeName, double newCoefficient) {

    }
}
