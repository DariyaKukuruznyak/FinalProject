package com.kukuruznyak.bettingcompany.dao.factory;

import com.kukuruznyak.bettingcompany.dao.*;
import com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql.MySqlDaoFactory;
import com.kukuruznyak.bettingcompany.dao.impl.jdbc.postgresql.PostgreSqlDAOFactory;

import java.sql.Connection;

public abstract class DaoFactory {

    public abstract BetDao getBetDao(Connection connection);

    public abstract BetItemDao getBetItemDao(Connection connection);

    public abstract TournamentDao getTournamentDao(Connection connection);

    public abstract ClientDao getClientDao(Connection connection);

    public abstract UserDao getUserDao(Connection connection);

    public abstract EventDao getEventDao(Connection connection);

    public abstract MarketDao getMarketDao(Connection connection);

    public abstract OutcomeDao getOutcomeDao(Connection connection);

    public abstract ParticipantDao getParticipantDao(Connection connection);

    public static DaoFactory getDaoFactory(DaoFactoryType factoryType) {
        switch (factoryType) {
            case MYSQL:
                return MySqlDaoFactory.getInstance();
            case POSTGRESQL:
                return PostgreSqlDAOFactory.getInstance();
            default:
                return MySqlDaoFactory.getInstance();
        }
    }
}
