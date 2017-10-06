package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.MarketDao;
import com.kukuruznyak.bettingcompany.dao.OutcomeDao;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.MarketNames;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.OutcomeBuilder;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;

import java.util.Collection;

public class EventService extends AbstractService {
    private static EventService instance;
    private EventDao eventDao = daoFactory.getEventDao();
    private MarketDao marketDao = daoFactory.getMarketDao();
    private OutcomeDao outcomeDao = daoFactory.getOutcomeDao();

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

    public Event add(Event event) {
        return eventDao.add(event);
    }

    public Event getById(String id) {
        return eventDao.getById(new Long(id));
    }

    public Collection<Event> getEventsByBookmakerId(Long id) {
        return eventDao.getAllByBookmakerId(id);
    }

    public Collection<Event> getAll() {
        return eventDao.getAll();
    }

    public Event createMarket(Event event, MarketNames marketNames) {
        Market market = marketDao.add(new Market(marketNames, event));
        for (Participant participant : event.getTournament().getParticipants()) {
            Outcome outcome = new OutcomeBuilder()
                    .buildName(participant.getFullName())
                    .buildMarket(market)
                    .build();
            market.addOutcome(outcomeDao.add(outcome));
        }
        event.addMarket(market);
        return event;
    }
}
