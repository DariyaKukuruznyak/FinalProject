package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAOFactory extends DaoFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/totalizator";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public MySqlDAOFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public BetDao getBetDao(Connection connection) {
        return new BetDaoMySql(connection);
    }

    @Override
    public ClientDao getClientDao(Connection connection) {
        return new ClientDaoMySql(connection);
    }

    @Override
    public EmployeeDao getEmployeeDao(Connection connection) {
        return new EmployeeDaoMySql(connection);
    }

    @Override
    public EventDao getEventDao(Connection connection) {
        return new EventDaoMySql(connection);
    }

    @Override
    public ParticipantDao getParticipantDao(Connection connection) {
        return new ParticipantDaoMySql(connection);
    }
}
