package com.kukuruznyak.bettingcompany.dao.impl.jdbc.postgresql;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;

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
