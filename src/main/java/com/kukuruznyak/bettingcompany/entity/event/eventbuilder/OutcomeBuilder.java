package com.kukuruznyak.bettingcompany.entity.event.eventbuilder;

import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;

public class OutcomeBuilder {
    private Long id;
    private String name;
    private double coefficient = 1;
    private Market market;

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

    public OutcomeBuilder buildMarket(Market market) {
        this.market = market;
        return this;
    }

    public Outcome build() {
        Outcome outcome = new Outcome();
        outcome.setId(this.id);
        outcome.setName(this.name);
        outcome.setCoefficient(this.coefficient);
        outcome.setMarket(this.market);
        return outcome;
    }
}
