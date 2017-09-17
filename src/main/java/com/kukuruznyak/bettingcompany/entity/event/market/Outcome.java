package com.kukuruznyak.bettingcompany.entity.event.market;

import com.kukuruznyak.bettingcompany.entity.enums.OutcomeNames;

public abstract class Outcome {
    private OutcomeNames name;
    private double coefficient;

    public Outcome(OutcomeNames name) {
        this.name = name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return name + ": " + coefficient;
    }
}
