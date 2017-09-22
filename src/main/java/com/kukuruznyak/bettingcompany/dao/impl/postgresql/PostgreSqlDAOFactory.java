package com.kukuruznyak.bettingcompany.dao.impl.postgresql;

import com.kukuruznyak.bettingcompany.dao.*;

import java.sql.Connection;

public class PostgreSqlDAOFactory extends DaoFactory {
    private static PostgreSqlDAOFactory instance;

    public static PostgreSqlDAOFactory getInstance() {
        if (instance == null) {
            instance = new PostgreSqlDAOFactory();
        }
        return instance;
    }

    private PostgreSqlDAOFactory() {
    }


    @Override
    public BetDao getBetDao() {
        return null;
    }

    @Override
    public ClientDao getClientDao() {
        return null;
    }

    @Override
    public EmployeeDao getEmployeeDao() {
        return null;
    }

    @Override
    public EventDao getEventDao() {
        return null;
    }

    @Override
    public ParticipantDao getParticipantDao() {
        return null;
    }
}
