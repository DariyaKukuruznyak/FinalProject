package com.kukuruznyak.bettingcompany.dao.impl.postgresql;

import com.kukuruznyak.bettingcompany.dao.interfaces.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.interfaces.GenericDao;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgreSqlDaoFactory implements DaoFactory{
    public Connection getConnection() throws SQLException {
        return null;
    }

    public GenericDao getGenericDao(Connection connection) {
        return null;
    }
}
