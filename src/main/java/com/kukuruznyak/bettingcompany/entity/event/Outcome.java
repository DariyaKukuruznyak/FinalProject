package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;

public class Outcome extends Model {
    private Participant participant;
    private double coefficient;

    public Outcome() {
    }

    public Outcome(Long id) {
        super(id);
    }

    public Outcome(Participant participant) {
        this.participant = participant;
    }

    public Outcome(Participant participant, double coefficient) {
        this.participant = participant;
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return participant.getName() + ": " + coefficient;
    }
}
