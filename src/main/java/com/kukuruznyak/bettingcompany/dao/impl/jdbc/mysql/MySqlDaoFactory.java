package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;

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
    public BetDao getBetDao() {
        return MySqlBetDaoImpl.getInstance();
    }

    @Override
    public ClientDao getClientDao() {
        return MySqlClientDaoImpl.getInstance();
    }

    @Override
    public EventDao getEventDao() {
        return MySqlEventDaoImpl.getInstance();
    }

    @Override
    public ParticipantDao getParticipantDao() {
        return MySqlParticipantDaoImpl.getInstance();
    }
}
