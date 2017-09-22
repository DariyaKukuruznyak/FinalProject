package com.kukuruznyak.bettingcompany.dao.impl.mysql;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.dao.connection.ConnectionPool;
import com.kukuruznyak.bettingcompany.entity.Bet;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class MySqlBetDao implements BetDao {
    private static MySqlBetDao instance;
    private DataSource dataSource = ConnectionPool.getInstance().getConnectionPool();

    public static MySqlBetDao getInstance() {
        if (instance == null) {
            instance = new MySqlBetDao();
        }
        return instance;
    }

    private MySqlBetDao() {
    }

    @Override
    public Bet getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Bet> getAll() throws SQLException {
        return null;
    }

    @Override
    public void add(Bet model) throws SQLException {

    }

    @Override
    public void update(Bet model) throws SQLException {

    }

    @Override
    public void delete(Bet model) throws SQLException {

    }
}