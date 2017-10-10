package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.BetItemDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.bet.BetItem;
import com.kukuruznyak.bettingcompany.entity.bet.ResultOfBet;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlBetItemDaoImpl extends AbstractDaoImpl<BetItem> implements BetItemDao {
    private static MySqlBetItemDaoImpl instance;
    private static final String SELECT_BY_BET_ID = "selectByBetId";
    private static final String SELECT_BY_OUTCOME_ID = "selectByOutcomeId";

    public static MySqlBetItemDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlBetItemDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlBetItemDaoImpl();
                }
            }
        }
        return instance;
    }

    private MySqlBetItemDaoImpl() {
        super(BetItem.class.getSimpleName());
    }

    public Collection<BetItem> getAllByBetId(Long id) throws PersistenceException {
        return super.getAllByConstrain(
                QUERIES.getString(currentModel + "." + SELECT_BY_BET_ID),
                String.valueOf(id));
    }

    public Collection<BetItem> getAllByOutcomeId(Long id) throws PersistenceException {
        return super.getAllByConstrain(
                QUERIES.getString(currentModel + "." + SELECT_BY_OUTCOME_ID),
                String.valueOf(id));
    }

    @Override
    public BetItem getById(Long id) throws PersistenceException {
        throw new PersistenceException("Unexpected request!");
    }

    @Override
    public Collection<BetItem> getAll() throws PersistenceException {
        throw new PersistenceException("Unexpected request!");
    }

    @Override
    protected BetItem fillModel(ResultSet resultSet) throws PersistenceException {
        BetItem betItem = new BetItem();
        try {
            betItem.setId(resultSet.getLong("id"));
            betItem.setName(resultSet.getString("name"));
            betItem.setCoefficient(resultSet.getDouble("coefficient"));
            betItem.setResult(ResultOfBet.valueOf(resultSet.getString("result")));
            betItem.setOutcomeId(resultSet.getLong("outcome_id"));
            betItem.setBetId(resultSet.getLong("bet_id"));
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return betItem;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, BetItem betItem) throws PersistenceException {
        try {
            preparedStatement.setString(1, betItem.getName());
            preparedStatement.setDouble(2, betItem.getCoefficient());
            preparedStatement.setString(3, betItem.getResult().toString());
            preparedStatement.setLong(4, betItem.getOutcomeId());
            preparedStatement.setLong(5, betItem.getBetId());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}