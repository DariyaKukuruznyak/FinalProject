package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.entity.event.Event;

import java.util.Collection;

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

    public void createMarkets(Event event) {

    }
}
