package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.MarketNames;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.EventBuilder;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;

public class EventService extends AbstractService {
    private static EventService instance;
    private EventDao eventDao = daoFactory.getEventDao();

    public static EventService getInstance() {
        if (instance == null) {
            synchronized (EventService.class) {
                if (instance == null) {
                    instance = new EventService();
                }
            }
        }
        return instance;
    }

    private EventService() {
    }

    public Event getEventByTournamentId(Long id) {
        Event event = new EventBuilder().build();
        Tournament tournament = new Tournament();
        tournament.setName("Tournament");
        event.setTournament(tournament);
        Market market = new Market(MarketNames.WINNER);
        Outcome outcome = new Outcome();
        outcome.setName("Black horse");
        outcome.setCoefficient(2.50);
        market.addOutcome(outcome);
        market.addOutcome(outcome);
        market.addOutcome(outcome);
        event.addMarket(market);
        return event;
    }

    public void add(Event event) {
        eventDao.add(event);
    }
}
