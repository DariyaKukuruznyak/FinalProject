package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Market extends Model {
    private MarketNames name;
    private Collection<Outcome> outcomes;
    private Event event;

    public Market() {
    }

    public Market(MarketNames name) {
        this.name = name;
    }

    public Market(MarketNames name, Event event) {
        this.name = name;
        this.event = event;
    }

    public MarketNames getName() {
        return name;
    }

    public void setName(MarketNames name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getMargin() {
        return 1.07;
    }//TODO

    public void setOutcomes(Collection<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    public Collection<Outcome> getOutcomes() {
        return outcomes;
    }

    public void addOutcome(Outcome outcomes) {
        if (this.outcomes == null) {
            this.outcomes = new HashSet<>();
        }
        this.outcomes.add(outcomes);
    }

}
