package com.kukuruznyak.bettingcompany.entity.event.eventbuilder;

import com.kukuruznyak.bettingcompany.entity.event.*;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EventBuilder {
    private Calendar creationDateAndTime = new GregorianCalendar();
    private Tournament tournament;
    private User bookmaker;
    private double margin = 1.07;
    private double maxWin = 1000;

    EventBuilder buildDate(Calendar date) {
        this.creationDateAndTime = date;
        return this;
    }

    EventBuilder buildTournament(Tournament tournament) {
        this.tournament = tournament;
        return this;
    }

    EventBuilder buildBookmaker(User bookmaker) {
        this.bookmaker = bookmaker;
        return this;
    }

    EventBuilder buildMargin(double margin) {
        this.margin = margin;
        return this;
    }

    EventBuilder buildMaxWin(double maxWin) {
        this.maxWin = maxWin;
        return this;
    }

    public Event build() {
        Event event = new Event();
        event.setCreationDateAndTime(this.creationDateAndTime);
        event.setTournament(this.tournament);
        event.setBookmaker(this.bookmaker);
        event.setMargin(this.margin);
        event.setMaxWin(this.maxWin);
        event.addMarket(new Market(MarketNames.WINNER));
        return event;
    }
}
