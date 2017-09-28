package com.kukuruznyak.bettingcompany.dao.factory;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql.MySqlDaoFactory;
import com.kukuruznyak.bettingcompany.dao.impl.jdbc.postgresql.PostgreSqlDAOFactory;

public abstract class DaoFactory {

    public abstract BetDao getBetDao();

    public abstract ClientDao getClientDao();

    public abstract EventDao getEventDao();

    public abstract ParticipantDao getParticipantDao();

    public static DaoFactory getDAOFactory(DaoFactoryType factoryType) {
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
