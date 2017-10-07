package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.MarketDao;
import com.kukuruznyak.bettingcompany.dao.OutcomeDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.MarketNames;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlMarketDaoImpl extends AbstractDaoImpl<Market> implements MarketDao {
    private static MySqlMarketDaoImpl instance;
    private static EventDao eventDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getEventDao();
    private static OutcomeDao outcomeDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getOutcomeDao();

    private static final String GET_MARKETS_BY_EVENT_ID = "selectAllByEventId";

    public static MySqlMarketDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlMarketDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlMarketDaoImpl();
                    LOGGER.info("Instance of " + MySqlMarketDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlMarketDaoImpl() {
        super(Market.class.getSimpleName());
    }

    @Override
    protected Market fillModel(ResultSet resultSet) throws PersistenceException {
        Market market = new Market();
        try {
            market.setId(resultSet.getLong("id"));
            market.setName(MarketNames.valueOf(resultSet.getString("name")));
            market.setEventId(resultSet.getLong("event_id"));
            market.setOutcomes(outcomeDao.getAllByMarketId(market.getId()));
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return market;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Market market) throws PersistenceException {
        try {
            preparedStatement.setString(1, market.getName().toString());
            preparedStatement.setLong(2, market.getEventId());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Market getById(Long id) throws PersistenceException {
        Market market = super.getById(id);
        market.setOutcomes(outcomeDao.getAllByMarketId(market.getId()));
        return market;
    }

    @Override
    public Collection<Market> getAll() throws PersistenceException {
        Collection<Market> markets = super.getAll();
        for (Market market : markets) {
            market.setOutcomes(outcomeDao.getAllByMarketId(market.getId()));
        }
        return markets;
    }

    public Collection<Market> getAllByEventId(Long eventId) throws PersistenceException {
        return super.getAllByConstrain(QUERIES.getString(currentModel + "." + GET_MARKETS_BY_EVENT_ID),
                String.valueOf(eventId));
    }
}