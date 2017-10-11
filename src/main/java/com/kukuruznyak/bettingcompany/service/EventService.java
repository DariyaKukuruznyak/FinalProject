package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.MarketDao;
import com.kukuruznyak.bettingcompany.dao.OutcomeDao;
import com.kukuruznyak.bettingcompany.entity.event.*;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.OutcomeBuilder;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EventService extends AbstractService {
    private static EventService instance;
    private EventDao eventDao = daoFactory.getEventDao();
    private MarketDao marketDao = daoFactory.getMarketDao();
    private OutcomeDao outcomeDao = daoFactory.getOutcomeDao();
    private BetService betService = ServiceFactory.getInstance().getBetService();

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
        return getById(new Long(id));
    }

    public Event getById(Long id) {
        Event event = eventDao.getById(id);
        sortOutcomes(event);
        return event;
    }

    public Collection<Event> getEventsByBookmakerId(Long id) {
        Collection<Event> events = eventDao.getAllByBookmakerId(id);
        for (Event event : events) {
            sortOutcomes(event);
        }
        return events;
    }

    public Collection<Event> getAll() {
        Collection<Event> events = eventDao.getAll();
        for (Event event : events) {
            sortOutcomes(event);
        }
        return events;
    }

    public Event createMarket(Event event, MarketNames marketNames) {
        Market market = marketDao.add(new Market(marketNames, event.getId()));
        for (Participant participant : event.getTournament().getParticipants()) {
            Outcome outcome = new OutcomeBuilder()
                    .buildName(participant.getFullName())
                    .buildMarketId(market.getId())
                    .build();
            market.addOutcome(outcomeDao.add(outcome));
        }
        event.addMarket(market);
        return event;
    }

    public void update(Event event) {
        eventDao.update(event);
    }

    private void sortOutcomes(Event event) {
        if (event.getMarkets() != null) {
            for (Market market : event.getMarkets()) {
                List<Outcome> outcomes = new ArrayList<>(market.getOutcomes());
                Collections.sort(outcomes);
                market.setOutcomes(outcomes);
            }
        }
    }

    public void finishEvent(Event event) {
        betService.calculateBets(event);
    }

    public Collection<Event> getEvensByStatus(EventStatus status) {
        return eventDao.getAllByStatus(status);
    }
}
