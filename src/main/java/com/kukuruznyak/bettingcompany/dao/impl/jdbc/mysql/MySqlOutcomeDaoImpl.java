package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.MarketDao;
import com.kukuruznyak.bettingcompany.dao.OutcomeDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.OutcomeBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlOutcomeDaoImpl extends AbstractDaoImpl<Outcome> implements OutcomeDao {
    private static MySqlOutcomeDaoImpl instance;
    private static MarketDao marketDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getMarketDao();

    private static final String GET_EVENTS_BY_MARKET_ID="selectAllByMarketId";

    public static MySqlOutcomeDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlOutcomeDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlOutcomeDaoImpl();
                    LOGGER.info("Instance of " + MySqlOutcomeDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlOutcomeDaoImpl() {
        super(Outcome.class.getSimpleName());
    }

    @Override
    protected Outcome fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new OutcomeBuilder()
                    .buildId(resultSet.getLong("id"))
                    .buildName(resultSet.getString("name"))
                    .buildCoefficient(resultSet.getDouble("coefficient"))
                    .buildMarket(marketDao.getById(resultSet.getLong("market_id")))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Outcome outcome) throws PersistenceException {
        try {
            preparedStatement.setString(1, outcome.getName());
            preparedStatement.setDouble(2, outcome.getCoefficient());
            preparedStatement.setLong(3, outcome.getMarket().getId());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Collection<Outcome> getAllByMarketId(Long marketId) {
        return super.getAllByConstrain(QUERIES.getString(currentModel + "." + GET_EVENTS_BY_MARKET_ID),
                String.valueOf(marketId));
    }
}