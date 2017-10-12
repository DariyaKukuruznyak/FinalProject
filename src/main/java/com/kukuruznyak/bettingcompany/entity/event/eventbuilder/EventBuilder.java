package com.kukuruznyak.bettingcompany.entity.event.eventbuilder;

import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.math.BigDecimal;
import java.util.Date;

public class EventBuilder {
    private Long id = null;
    private Date creationDate = new Date();
    private Tournament tournament;
    private User bookmaker;
    private EventStatus eventStatus = EventStatus.LOCKED;
    private boolean isSuspended = true;
    private BigDecimal turnover = new BigDecimal(0);
    private BigDecimal profit = new BigDecimal(0);

    public EventBuilder buildId(Long id) {
        this.id = id;
        return this;
    }

    public EventBuilder buildDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public EventBuilder buildTournament(Tournament tournament) {
        this.tournament = tournament;
        return this;
    }

    public EventBuilder buildBookmaker(User bookmaker) {
        this.bookmaker = bookmaker;
        return this;
    }

    public EventBuilder buildStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
        return this;
    }

    public EventBuilder buildIsSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
        return this;
    }

    public EventBuilder buildTurnover(BigDecimal turnover) {
        this.turnover = turnover;
        return this;
    }

    public EventBuilder buildProfit(BigDecimal profit) {
        this.profit = profit;
        return this;
    }

    public Event build() {
        Event event = new Event();
        event.setId(this.id);
        event.setCreationDate(this.creationDate);
        event.setTournament(this.tournament);
        event.setBookmaker(this.bookmaker);
        event.setStatus(this.eventStatus);
        event.setSuspended(this.isSuspended);
        event.setTurnover(this.turnover);
        event.setProfit(this.profit);
        return event;
    }
}
