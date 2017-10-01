package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlTournamentDaoImpl extends AbstractDaoImpl<Tournament> implements TournamentDao {
    private static MySqlTournamentDaoImpl instance;

    public static MySqlTournamentDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlTournamentDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlTournamentDaoImpl();
                    LOGGER.info("Instance of " + MySqlTournamentDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlTournamentDaoImpl() {
        super(Tournament.class.getSimpleName());
    }

    @Override
    protected Tournament fillModel(ResultSet resultSet) throws PersistenceException {
        return null;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Tournament model) throws PersistenceException {

    }
}