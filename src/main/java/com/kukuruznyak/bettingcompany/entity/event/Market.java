package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.HashSet;
import java.util.Set;

public class Market extends Model {
    private MarketNames name;
    private Set<Outcome> outcomes;
    public MarketNames getName() {
        return name;
    }

    public Market() {
    }

    public Market(MarketNames name) {
        this.name = name;
    }

    public void setName(MarketNames name) {
        this.name = name;
    }

    public Set<Outcome> getOutcomes() {
        return outcomes;
    }

    public void addOutcome(Outcome outcomes) {
        if (this.outcomes==null) {
            this.outcomes = new HashSet<>();
        }
        this.outcomes.add(outcomes);
    }

}
