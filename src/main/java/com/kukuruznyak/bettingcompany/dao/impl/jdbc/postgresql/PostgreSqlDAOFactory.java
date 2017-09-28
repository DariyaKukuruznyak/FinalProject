package com.kukuruznyak.bettingcompany.dao.impl.jdbc.postgresql;

import com.kukuruznyak.bettingcompany.dao.*;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;

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
    public EventDao getEventDao() {
        return null;
    }

    @Override
    public ParticipantDao getParticipantDao() {
        return null;
    }
}
