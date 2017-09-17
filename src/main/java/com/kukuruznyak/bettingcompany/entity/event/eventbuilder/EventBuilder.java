package com.kukuruznyak.bettingcompany.entity.event.eventbuilder;

import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.market.marketbuilder.MarketBuilderDirector;
import com.kukuruznyak.bettingcompany.entity.event.market.marketbuilder.WinLoseMarketBuilder;
import com.kukuruznyak.bettingcompany.entity.event.market.marketbuilder.WinnerMarketBuilder;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EventBuilder {
    private Calendar startDateAndTime = new GregorianCalendar();
    private String country;
    private String tournament;
    private User bookmaker;
    private double margin = 1.07;
    private double maxWin = 1000;

    EventBuilder buildDate(Calendar date) {
        this.startDateAndTime = date;
        return this;
    }

    EventBuilder buildCountry(String country) {
        this.country = country;
        return this;
    }

    EventBuilder buildTournament(String tournament) {
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

    Event build() {
        Event event = new Event();
        event.setStartDateAndTime(this.startDateAndTime);
        event.setCountry(this.country);
        event.setTournament(this.tournament);
        event.setBookmaker(this.bookmaker);
        event.setMargin(this.margin);
        event.setMaxWin(this.maxWin);

        MarketBuilderDirector marketBuilderDirector = new MarketBuilderDirector();
        marketBuilderDirector.setBuilder(new WinnerMarketBuilder());
        event.addMarket(marketBuilderDirector.buildMarket());
        marketBuilderDirector.setBuilder(new WinLoseMarketBuilder());
        event.addMarket(marketBuilderDirector.buildMarket());

        return event;
    }
}
