package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Participant;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlParticipantDaoImpl extends AbstractDaoImpl<Participant> implements ParticipantDao {
    private static MySqlParticipantDaoImpl instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();

    public static MySqlParticipantDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlParticipantDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlParticipantDaoImpl();
                    LOGGER.info("Instance of " + MySqlParticipantDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlParticipantDaoImpl() {
        super(Participant.class.getSimpleName());
    }

    @Override
    protected Participant fillModel(ResultSet resultSet) throws PersistenceException {
        return null;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Participant model) throws PersistenceException {

    }
}
