package com.kukuruznyak.bettingcompany.dao.impl.postgresql.impl;

import com.kukuruznyak.bettingcompany.dao.impl.postgresql.PostgreSqlGenericDao;
import com.kukuruznyak.bettingcompany.entity.event.Event;

import java.sql.SQLException;
import java.util.Set;

public class EventDao extends PostgreSqlGenericDao<Event, Long> {

    public Event create() {
        return null;
    }

    public Event read(Long id) {
        return null;
    }

    public void update(Event object) {

    }

    public void delete(Long id) {

    }

    public Set<Event> getAll() throws SQLException {
        return null;
    }
}
