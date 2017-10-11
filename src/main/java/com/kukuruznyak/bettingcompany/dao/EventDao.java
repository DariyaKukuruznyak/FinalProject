package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;

import java.util.Collection;

public interface EventDao extends AbstractDao<Event> {
    Collection<Event> getAllByBookmakerId(Long bookmakerId);

    Collection<Event> getAllByStatus(EventStatus eventStatus);

    Event getByBetItemId(Long betItemId);
}
