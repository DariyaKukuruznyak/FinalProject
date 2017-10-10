package com.kukuruznyak.bettingcompany.dao.factory;

import com.kukuruznyak.bettingcompany.dao.*;
import com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql.MySqlDaoFactory;
import com.kukuruznyak.bettingcompany.dao.impl.jdbc.postgresql.PostgreSqlDAOFactory;

public abstract class DaoFactory {

    public abstract BetDao getBetDao();

    public abstract BetItemDao getBetItemDao();

    public abstract TournamentDao getTournamentDao();

    public abstract ClientDao getClientDao();

    public abstract UserDao getUserDao();

    public abstract EventDao getEventDao();

    public abstract MarketDao getMarketDao();

    public abstract OutcomeDao getOutcomeDao();

    public abstract ParticipantDao getParticipantDao();

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
