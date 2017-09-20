package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.entity.event.Event;

import java.sql.Connection;
import java.util.List;

public class EventDaoMySql implements EventDao {
    private final Connection connection;

    public EventDaoMySql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Event getById(Long id) {
        return null;
    }

    @Override
    public List<Event> getAll() {
        return null;
    }

    @Override
    public void add(Event model) {

    }

    @Override
    public void update(Event model) {

    }

    @Override
    public void delete(Event model) {

    }
}
