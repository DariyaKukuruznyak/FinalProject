package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;

public class Outcome extends Model {
    private String name;
    private double coefficient;

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

    @Override
    public String toString() {
        return name + ": " + coefficient;
    }
}
