package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlClientDaoImpl extends AbstractDaoImpl<Client> implements ClientDao {
    private static final String BALANCE_COLUMN = "balance";
    private static final String MAX_BET_COLUMN = "max_bet";
    private static final String DESCRIPTION_COLUMN = "description";

    public MySqlClientDaoImpl(Connection connection) {
        super(connection, Client.class.getSimpleName());
    }

    @Override
    protected Client fillModel(ResultSet resultSet) {
        Client client = new Client(new MySqlUserDaoImpl(connection).fillModel(resultSet));
        try {
            client.setBalance(resultSet.getBigDecimal(BALANCE_COLUMN));
            client.setMaxBet(resultSet.getInt(MAX_BET_COLUMN));
            client.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return client;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Client client) {
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
