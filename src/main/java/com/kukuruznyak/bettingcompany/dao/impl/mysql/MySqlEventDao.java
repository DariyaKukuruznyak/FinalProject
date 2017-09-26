package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.entity.event.Event;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MySqlEventDao implements EventDao {
    private static MySqlEventDao instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();

    public static MySqlEventDao getInstance() {
        if (instance == null) {
            instance = new MySqlEventDao();
        }
        return instance;
    }

    private MySqlEventDao() {
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
    public Event add(Event event) {
        return null;
    }

    @Override
    public void update(Event model) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
