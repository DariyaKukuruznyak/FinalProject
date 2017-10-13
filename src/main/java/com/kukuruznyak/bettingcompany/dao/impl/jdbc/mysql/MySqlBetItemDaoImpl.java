package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.BetItemDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.bet.BetItem;
import com.kukuruznyak.bettingcompany.entity.bet.ResultOfBet;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlBetItemDaoImpl extends AbstractDaoImpl<BetItem> implements BetItemDao {
    private static final String SELECT_BY_BET_ID_QUERY = "selectByBetId";
    private static final String SELECT_BY_OUTCOME_ID_QUERY = "selectByOutcomeId";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String COEFFICIENT_COLUMN = "coefficient";
    private static final String RESULT_COLUMN = "result";
    private static final String OUTCOME_ID_COLUMN = "outcome_id";
    private static final String BET_ID_COLUMN = "bet_id";

    public MySqlBetItemDaoImpl(Connection connection) {
        super(connection, BetItem.class.getSimpleName());
    }

    public Collection<BetItem> getAllByBetId(Long id) {
        return super.getAllByConstrain(
                QUERIES.getString(currentModel + DELIMITER + SELECT_BY_BET_ID_QUERY),
                String.valueOf(id));
    }

    public Collection<BetItem> getAllByOutcomeId(Long id) throws PersistenceException {
        return super.getAllByConstrain(
                QUERIES.getString(currentModel + DELIMITER + SELECT_BY_OUTCOME_ID_QUERY),
                String.valueOf(id));
    }

    @Override
    public BetItem getById(Long id) {
        throw new PersistenceException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
    }

    @Override
    public Collection<BetItem> getAll() {
        throw new PersistenceException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
    }

    @Override
    protected BetItem fillModel(ResultSet resultSet) {
        BetItem betItem = new BetItem();
        try {
            betItem.setId(resultSet.getLong(ID_COLUMN));
            betItem.setName(resultSet.getString(NAME_COLUMN));
            betItem.setCoefficient(resultSet.getDouble(COEFFICIENT_COLUMN));
            betItem.setResult(ResultOfBet.valueOf(resultSet.getString(RESULT_COLUMN)));
            betItem.setOutcomeId(resultSet.getLong(OUTCOME_ID_COLUMN));
            betItem.setBetId(resultSet.getLong(BET_ID_COLUMN));
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return betItem;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, BetItem betItem) {
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