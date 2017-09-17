package com.kukuruznyak.bettingcompany.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {
    Connection getConnection() throws SQLException;

    GenericDao getGenericDao(Connection connection);

}

