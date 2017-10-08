package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlBetDaoImpl extends AbstractDaoImpl<Bet> implements BetDao {
    private static MySqlBetDaoImpl instance;

    public static MySqlBetDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlBetDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlBetDaoImpl();
                }
            }
        }
        return instance;
    }

    private MySqlBetDaoImpl() {
        super(Bet.class.getSimpleName());
    }

    @Override
    protected Bet fillModel(ResultSet resultSet) throws PersistenceException {
        Bet bet = new Bet();
        try {
            bet.setSumIn(resultSet.getBigDecimal("sum_in"));
            bet.setSumOut(resultSet.getBigDecimal("sum_out"));
            bet.setType(TypeOfBet.valueOf(resultSet.getString("bet_type")));
            bet.setDescription(resultSet.getString("description"));
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return bet;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Bet model) throws PersistenceException {
        try {
            preparedStatement.setString(1, model.getSumIn().toString());
            preparedStatement.setString(2, model.getSumOut().toString());
            preparedStatement.setString(3, model.getType().toString());
            preparedStatement.setString(4, model.getResult().toString());
            preparedStatement.setString(5, model.getDescription());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}