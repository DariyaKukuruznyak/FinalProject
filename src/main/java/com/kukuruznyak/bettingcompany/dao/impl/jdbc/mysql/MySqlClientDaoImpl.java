package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlClientDaoImpl extends AbstractDaoImpl<Client> implements ClientDao {
    private static MySqlClientDaoImpl instance;
    private static UserDao userDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getUserDao();

    public static MySqlClientDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlClientDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlClientDaoImpl();
                    LOGGER.info("Instance of " + MySqlClientDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlClientDaoImpl() {
        super(Client.class.getSimpleName());
    }

    @Override
    public Client add(Client client) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            User user = userDao.add(client);
            client.setId(user.getId());
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + "." + INSERT));
            fillPreparedStatement(preparedStatement, client);
            int rowInserted = preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info(rowInserted + " row(s) inserted");
            LOGGER.info(currentModel + " was inserted. Details: " + client.toString());
        } catch (SQLException e) {
            LOGGER.error("Database error during inserting " + currentModel +
                    " with message: " + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
        return client;

    }

    @Override
    public void update(Client client) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            userDao.update(client);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel + "." + UPDATE));
            fillPreparedStatement(preparedStatement, client);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Database error during updating " + currentModel +
                    " with message: " + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws PersistenceException {
        userDao.delete(id);
    }

    @Override
    protected Client fillModel(ResultSet resultSet) throws PersistenceException {
        Client client;
        try {
            client = new Client(userDao.getById(resultSet.getLong("id")));
            client.setBalance(resultSet.getBigDecimal("balance"));
            client.setMaxBet(resultSet.getInt("max_bet"));
            client.setDescription(resultSet.getString("description"));
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return client;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Client client) throws PersistenceException {
        try {
            preparedStatement.setLong(1, client.getId());
            preparedStatement.setBigDecimal(2, client.getBalance());
            preparedStatement.setInt(3, client.getMaxBet());
            preparedStatement.setString(4, client.getDescription());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}
