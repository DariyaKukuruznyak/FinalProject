package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.dao.impl.mysql.MySqlDAOFactory;
import com.kukuruznyak.bettingcompany.dao.impl.postgresql.PostgreSqlDAOFactory;

import java.sql.Connection;

public abstract class DaoFactory {
    public enum DaoFactoryType {
        MYSQL, POSTGRESQL
    }

    public abstract BetDao getBetDao(Connection connection);

    public abstract ClientDao getClientDao(Connection connection);

    public abstract EmployeeDao getEmployeeDao(Connection connection);

    public abstract EventDao getEventDao(Connection connection);

    public abstract ParticipantDao getParticipantDao(Connection connection);

    public static DaoFactory getDAOFactory(
            DaoFactoryType factoryType) {
        switch (factoryType) {
            case MYSQL:
                return new MySqlDAOFactory();
            case POSTGRESQL:
                return new PostgreSqlDAOFactory();
            default:
                return null;
        }
    }
}
