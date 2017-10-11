package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlClientDaoImpl extends AbstractDaoImpl<Client> implements ClientDao {
    private static MySqlClientDaoImpl instance;
    private static UserDao userDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getUserDao();

    private static final String ID_COLUMN = "id";
    private static final String BALANCE_COLUMN = "balance";
    private static final String MAX_BET_COLUMN = "max_bet";
    private static final String DESCRIPTION_COLUMN = "description";

    public static MySqlClientDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlClientDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlClientDaoImpl();
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
            return addClient(connection, client);
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_INSERTING_ERROR) + currentModel +
                    StringMessages.getMessage(StringMessages.MESSAGE) + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void update(Client client) throws PersistenceException {
        try (Connection connection = dataSource.getConnection()) {
            updateClient(connection, client);
        } catch (SQLException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.DB_UPDATING_ERROR )+ currentModel +
                    StringMessages.getMessage(StringMessages.MESSAGE) + e.getMessage());
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
            client = new Client(userDao.getById(resultSet.getLong(ID_COLUMN)));
            client.setBalance(resultSet.getBigDecimal(BALANCE_COLUMN));
            client.setMaxBet(resultSet.getInt(MAX_BET_COLUMN));
            client.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
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

    private Client addClient(Connection connection, Client client) throws SQLException {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            User user = userDao.add(client);
            client.setId(user.getId());
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel +
                    DELIMITER + INSERT_QUERY));
            fillPreparedStatement(preparedStatement, client);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new PersistenceException(e.getMessage());
        }
        return client;
    }

    private void updateClient(Connection connection, Client client) throws SQLException {
        try {
            connection.setAutoCommit(false);
            userDao.update(client);
            PreparedStatement preparedStatement = connection.prepareStatement(QUERIES.getString(currentModel +
                    DELIMITER + UPDATE_QUERY));
            fillPreparedStatement(preparedStatement, client);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new PersistenceException(e.getMessage());
        }
    }
}
