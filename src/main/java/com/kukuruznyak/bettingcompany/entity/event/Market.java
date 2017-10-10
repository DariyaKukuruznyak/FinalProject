package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Market extends Model {
    private MarketNames name;
    private Collection<Outcome> outcomes;
    private Long eventId;

    public Market() {
    }

    public Market(MarketNames name) {
        this.name = name;
    }

    public Market(MarketNames name, Long eventId) {
        this.name = name;
        this.eventId = eventId;
    }

    public MarketNames getName() {
        return name;
    }

    public void setName(MarketNames name) {
        this.name = name;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public double getMargin() {
        double totalChance = 0;
        for (Outcome outcome : this.outcomes) {
            totalChance += 1 / outcome.getCoefficient();
        }
        double margin = (totalChance - 1) * 100;
        System.out.println(margin);
        return margin;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Market)) return false;

        Market market = (Market) o;

        if (getName() != market.getName()) return false;
        return getEventId().equals(market.getEventId());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getEventId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Market{" +
                "name=" + name +
                ", outcomes=" + outcomes +
                ", eventId=" + eventId +
                '}';
    }
}
