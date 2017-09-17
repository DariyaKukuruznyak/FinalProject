package com.kukuruznyak.bettingcompany.entity.event.market;

import com.kukuruznyak.bettingcompany.entity.enums.OutcomeNames;

public class OneOutcomeMarketItem extends MarketItem {
    private Outcome outcome;
    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public void updateCoefficient(OutcomeNames outcomeName, double newCoefficient) {

    }
}
