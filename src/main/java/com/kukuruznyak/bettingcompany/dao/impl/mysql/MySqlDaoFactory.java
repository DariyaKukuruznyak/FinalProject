package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.*;

public class MySqlDaoFactory extends DaoFactory {
    private static MySqlDaoFactory instance;

    public static MySqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    private MySqlDaoFactory() {
    }

    @Override
    public BetDao getBetDao() {
        return MySqlBetDao.getInstance();
    }

    @Override
    public ClientDao getClientDao() {
        return MySqlClientDao.getInstance();
    }

    @Override
    public EmployeeDao getEmployeeDao() {
        return MySqlEmployeeDao.getInstance();
    }

    @Override
    public EventDao getEventDao() {
        return MySqlEventDao.getInstance();
    }

    @Override
    public ParticipantDao getParticipantDao() {
        return MySqlParticipantDao.getInstance();
    }
}
