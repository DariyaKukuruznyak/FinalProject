package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

public abstract class AbstractService {
    protected static final Logger LOGGER = Logger.getLogger(AbstractService.class);
    protected static DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL);
    protected DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();
}
