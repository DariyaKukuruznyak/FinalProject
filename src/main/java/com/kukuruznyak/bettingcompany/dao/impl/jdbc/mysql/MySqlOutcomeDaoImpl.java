package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.OutcomeDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.OutcomeBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlOutcomeDaoImpl extends AbstractDaoImpl<Outcome> implements OutcomeDao {

    private static final String GET_EVENTS_BY_MARKET_ID_QUERY = "selectAllByMarketId";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String COEFFICIENT_COLUMN = "coefficient";
    private static final String MARKET_COLUMN = "market_id";

    public MySqlOutcomeDaoImpl(Connection connection) {
        super(connection, Outcome.class.getSimpleName());
    }

    @Override
    protected Outcome fillModel(ResultSet resultSet){
        try {
            return new OutcomeBuilder()
                    .buildId(resultSet.getLong(ID_COLUMN))
                    .buildName(resultSet.getString(NAME_COLUMN))
                    .buildCoefficient(resultSet.getDouble(COEFFICIENT_COLUMN))
                    .buildMarketId(resultSet.getLong(MARKET_COLUMN))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Outcome outcome){
        try {
            preparedStatement.setString(1, outcome.getName());
            preparedStatement.setDouble(2, outcome.getCoefficient());
            preparedStatement.setLong(3, outcome.getMarketId());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Collection<Outcome> getAllByMarketId(Long marketId) {
        return super.getAllByConstrain(QUERIES.getString(currentModel + DELIMITER + GET_EVENTS_BY_MARKET_ID_QUERY),
                String.valueOf(marketId));
    }
}