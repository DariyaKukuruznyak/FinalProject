package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlEventDaoImpl extends AbstractDaoImpl<Event> implements EventDao {
    private static MySqlEventDaoImpl instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();

    public static MySqlEventDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlEventDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlEventDaoImpl();
                    LOGGER.info("Instance of " + MySqlEventDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlEventDaoImpl() {
        super(Event.class.getSimpleName());
    }

    @Override
    protected Event fillModel(ResultSet resultSet) throws PersistenceException {
        return null;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Event model) throws PersistenceException {

    }
}