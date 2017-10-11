package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.*;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;

import java.sql.Connection;

public class MySqlDaoFactory extends DaoFactory {
    private static MySqlDaoFactory instance;

    public static MySqlDaoFactory getInstance() {
        if (instance == null) {
            synchronized (MySqlDaoFactory.class) {
                if (instance == null) {
                    instance = new MySqlDaoFactory();
                }
            }
        }
        return instance;
    }

    private MySqlDaoFactory() {
    }

    @Override
    public BetDao getBetDao(Connection connection) {
        return new MySqlBetDaoImpl(connection);
    }

    @Override
    public BetItemDao getBetItemDao(Connection connection) {
        return new MySqlBetItemDaoImpl(connection);
    }

    @Override
    public TournamentDao getTournamentDao(Connection connection) {
        return new MySqlTournamentDaoImpl(connection);
    }

    @Override
    public ClientDao getClientDao(Connection connection) {
        return new MySqlClientDaoImpl(connection);
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return new MySqlUserDaoImpl(connection);
    }

    @Override
    public EventDao getEventDao(Connection connection) {
        return new MySqlEventDaoImpl(connection);
    }

    @Override
    public MarketDao getMarketDao(Connection connection) {
        return new MySqlMarketDaoImpl(connection);
    }

    @Override
    public OutcomeDao getOutcomeDao(Connection connection) {
        return new MySqlOutcomeDaoImpl(connection);
    }

    @Override
    public ParticipantDao getParticipantDao(Connection connection) {
        return new MySqlParticipantDaoImpl(connection);
    }

}
