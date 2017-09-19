package com.kukuruznyak.bettingcompany.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcConnection {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    private final String URL = "";
    private final String USER_NAME = "root";
    private final String PASSWORD = "root";
}
