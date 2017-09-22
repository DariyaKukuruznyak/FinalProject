package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.entity.user.Client;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class MySqlClientDao implements ClientDao {
    private static MySqlClientDao instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();

    public static MySqlClientDao getInstance() {
        if (instance == null) {
            instance = new MySqlClientDao();
        }
        return instance;
    }

    private MySqlClientDao() {
    }

    @Override
    public Client getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Client> getAll() throws SQLException {
        return null;
    }

    @Override
    public void add(Client model) throws SQLException {

    }

    @Override
    public void update(Client model) throws SQLException {

    }

    @Override
    public void delete(Client model) throws SQLException {

    }
}
