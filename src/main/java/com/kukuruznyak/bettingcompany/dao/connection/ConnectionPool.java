package com.kukuruznyak.bettingcompany.dao.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.kukuruznyak.bettingcompany.util.StringMessages.CONNECTION_POOL_INITIALIZED;
import static com.kukuruznyak.bettingcompany.util.StringMessages.DB_INITIALIZATION_ERROR;

public class ConnectionPool {
    private static ConnectionPool instance;

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static final String JDBC_PROPERTIES = "jdbc.properties";
    private static final String JDBC_DRIVER = "jdbc.driver";
    private static final String JDBC_USERNAME = "jdbc.username";
    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String JDBC_URL = "jdbc.url";
    private static final String POOL_SIZE = "jdbc.poolSize";

    private DataSource connectionPool;

    private ConnectionPool() {
        try {
            connectionPool = initializeDataSource();
            LOGGER.info(CONNECTION_POOL_INITIALIZED);
        } catch (Exception e) {
            LOGGER.error(DB_INITIALIZATION_ERROR + e.getMessage());
            throw new RuntimeException(DB_INITIALIZATION_ERROR + e.getMessage());
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public DataSource getConnectionPool() {
        return connectionPool;
    }

    private DataSource initializeDataSource() {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties properties = new Properties();
        try (InputStream inputStream = classLoader.getResourceAsStream(JDBC_PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        BasicDataSource connectionPool = new BasicDataSource();
        connectionPool.setDriverClassName(properties.getProperty(JDBC_DRIVER));
        connectionPool.setUsername(properties.getProperty(JDBC_USERNAME));
        connectionPool.setPassword(properties.getProperty(JDBC_PASSWORD));
        connectionPool.setUrl(properties.getProperty(JDBC_URL));
        connectionPool.setInitialSize(Integer.valueOf(properties.getProperty(POOL_SIZE)));
        return connectionPool;
    }
}
