package com.kukuruznyak.bettingcompany.entity.event.eventbuilder;

import com.kukuruznyak.bettingcompany.entity.event.Outcome;

public class OutcomeBuilder {
    private Long id = null;
    private String name;
    private double coefficient = 1;
    private Long marketId;

    public OutcomeBuilder buildId(Long id) {
        this.id = id;
        return this;
    }

    public OutcomeBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public OutcomeBuilder buildCoefficient(double coefficient) {
        this.coefficient = coefficient;
        return this;
    }

    public OutcomeBuilder buildMarketId(Long marketId) {
        this.marketId = marketId;
        return this;
    }

    public Outcome build() {
        Outcome outcome = new Outcome();
        outcome.setId(this.id);
        outcome.setName(this.name);
        outcome.setCoefficient(this.coefficient);
        outcome.setMarketId(this.marketId);
        return outcome;
    }
}
