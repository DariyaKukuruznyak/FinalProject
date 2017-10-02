package com.kukuruznyak.bettingcompany.entity.event.eventbuilder;

import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.MarketNames;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EventBuilder {
    private Calendar creationDateAndTime = new GregorianCalendar();
    private Tournament tournament;
    private User bookmaker;
    private int maxWin = 5000;
    private EventStatus eventStatus = EventStatus.NOT_STARTED;

    public EventBuilder buildDate(Calendar date) {
        this.creationDateAndTime = date;
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

    public EventBuilder buildMaxWin(int maxWin) {
        this.maxWin = maxWin;
        return this;
    }

    public Event build() {
        Event event = new Event();
        event.setCreationDateAndTime(this.creationDateAndTime);
        event.setTournament(this.tournament);
        event.setBookmaker(this.bookmaker);
        event.setMaxWin(this.maxWin);
        event.setStatus(this.eventStatus);
        event.addMarket(new Market(MarketNames.WINNER));
        return event;
    }
}
