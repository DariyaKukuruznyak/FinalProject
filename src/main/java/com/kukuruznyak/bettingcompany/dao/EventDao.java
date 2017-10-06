package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.util.Collection;

public interface EventDao extends AbstractDao<Event> {
    Collection<Event> getAllByBookmakerId(Long bookmakerId) throws PersistenceException;
}
