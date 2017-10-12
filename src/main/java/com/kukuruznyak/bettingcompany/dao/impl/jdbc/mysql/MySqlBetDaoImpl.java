package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.BetDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.ResultOfBet;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.bet.builder.BetBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlBetDaoImpl extends AbstractDaoImpl<Bet> implements BetDao {
    private static final String ID_COLUMN = "id";
    private static final String SUM_IN_COLUMN = "sum_in";
    private static final String SUM_OUT_COLUMN = "sum_out";
    private static final String TYPE_COLUMN = "type";
    private static final String RESULT_COLUMN = "result";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String DATE_COLUMN = "date";
    private static final String CLIENT_ID_COLUMN = "client_id";
    private static final String TOTAL_COEFFICIENT_COLUMN = "total_coefficient";

    private static final String SELECT_ALL_BY_CLIENT_ID_QUERY = "selectByClientId";
    private static final String SELECT_ALL_BY_OUTCOME_ID_QUERY = "selectByOutcomeId";
    private static final String ROWS_NUMBER_QUERY = "count";

    public MySqlBetDaoImpl(Connection connection) {
        super(connection, Bet.class.getSimpleName());
    }
    @Override
    public Collection<Bet> getByClientId(Long clientId) {
        return getAllByConstrain(QUERIES.getString(currentModel + DELIMITER + SELECT_ALL_BY_CLIENT_ID_QUERY),
                clientId.toString());
    }

    @Override
    public Collection<Bet> getByOutcomeId(Long outcomeId) {
        return getAllByConstrain(QUERIES.getString(currentModel + DELIMITER + SELECT_ALL_BY_OUTCOME_ID_QUERY),
                outcomeId.toString());
    }

    @Override
    protected Bet fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new BetBuilder()
                    .buildId(resultSet.getLong(ID_COLUMN))
                    .buildSumIn(resultSet.getBigDecimal(SUM_IN_COLUMN))
                    .buildSumOut(resultSet.getBigDecimal(SUM_OUT_COLUMN))
                    .buildType(TypeOfBet.valueOf(resultSet.getString(TYPE_COLUMN)))
                    .buildResult(ResultOfBet.valueOf(resultSet.getString(RESULT_COLUMN)))
                    .buildDescription(resultSet.getString(DESCRIPTION_COLUMN))
                    .buildDate(resultSet.getDate(DATE_COLUMN))
                    .buildClientId(resultSet.getLong(CLIENT_ID_COLUMN))
                    .buildTotalCoefficient(resultSet.getDouble(TOTAL_COEFFICIENT_COLUMN))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Bet bet) throws PersistenceException {
        try {
            preparedStatement.setString(1, bet.getSumIn().toString());
            preparedStatement.setString(2, bet.getSumOut().toString());
            preparedStatement.setString(3, bet.getType().toString());
            preparedStatement.setString(4, bet.getResult().toString());
            preparedStatement.setString(5, bet.getDescription());
            preparedStatement.setDate(6, new java.sql.Date(bet.getDate().getTime()));
            preparedStatement.setLong(7, bet.getClientId());
            preparedStatement.setDouble(8, bet.getTotalCoefficient());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}