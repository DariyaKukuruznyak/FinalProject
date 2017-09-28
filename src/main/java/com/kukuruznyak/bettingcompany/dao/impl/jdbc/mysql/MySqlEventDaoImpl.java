package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Event;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlEventDaoImpl extends AbstractDaoImpl<Event> implements EventDao {
    private static MySqlEventDaoImpl instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();

    public static MySqlEventDaoImpl getInstance() {
        if (instance == null) {
            instance = new MySqlEventDaoImpl();
        }
        return instance;
    }

    private MySqlEventDaoImpl() {
        super(Event.class.getSimpleName());
    }

    @Override
    protected Event fillModel(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Event model) throws SQLException {

    }
}