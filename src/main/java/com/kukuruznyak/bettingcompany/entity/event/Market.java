package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.HashSet;
import java.util.Set;

public class Market extends Model {
    private MarketNames name;
    private double margin;
    private Set<Outcome> outcomes;
    private Event event;

    public MarketNames getName() {
        return name;
    }

    public Market() {
    }

    public Market(MarketNames name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setName(MarketNames name) {
        this.name = name;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public Set<Outcome> getOutcomes() {
        return outcomes;
    }

    public void addOutcome(Outcome outcomes) {
        if (this.outcomes == null) {
            this.outcomes = new HashSet<>();
        }
        this.outcomes.add(outcomes);
    }

}
