package com.kukuruznyak.bettingcompany.dao.impl.jdbc.postgresql;

import com.kukuruznyak.bettingcompany.dao.*;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;

import java.sql.Connection;

public class PostgreSqlDAOFactory extends DaoFactory {
    private static PostgreSqlDAOFactory instance;

    public static PostgreSqlDAOFactory getInstance() {
        if (instance == null) {
            synchronized (PostgreSqlDAOFactory.class) {
                if (instance == null) {
                    instance = new PostgreSqlDAOFactory();
                }
            }
        }
        return instance;
    }

    private PostgreSqlDAOFactory() {
    }

    @Override
    public BetDao getBetDao(Connection connection) {
        return null;
    }

    @Override
    public BetItemDao getBetItemDao(Connection connection) {
        return null;
    }

    @Override
    public TournamentDao getTournamentDao(Connection connection) {
        return null;
    }

    @Override
    public ClientDao getClientDao(Connection connection) {
        return null;
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return null;
    }

    @Override
    public EventDao getEventDao(Connection connection) {
        return null;
    }

    @Override
    public MarketDao getMarketDao(Connection connection) {
        return null;
    }

    @Override
    public OutcomeDao getOutcomeDao(Connection connection) {
        return null;
    }

    @Override
    public ParticipantDao getParticipantDao(Connection connection) {
        return null;
    }
}
