package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.dao.impl.mysql.MySqlDaoFactory;
import com.kukuruznyak.bettingcompany.dao.impl.postgresql.PostgreSqlDAOFactory;

public abstract class DaoFactory {
    public enum DaoFactoryType {
        MYSQL, POSTGRESQL
    }

    public abstract BetDao getBetDao();

    public abstract ClientDao getClientDao();

    public abstract EventDao getEventDao();

    public abstract ParticipantDao getParticipantDao();

    public static DaoFactory getDAOFactory(
            DaoFactoryType factoryType) {
        switch (factoryType) {
            case MYSQL:
                return MySqlDaoFactory.getInstance();
            case POSTGRESQL:
                return PostgreSqlDAOFactory.getInstance();
            default:
                return null;
        }
    }
}
