package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.MarketDao;
import com.kukuruznyak.bettingcompany.dao.OutcomeDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.MarketNames;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlMarketDaoImpl extends AbstractDaoImpl<Market> implements MarketDao {
    private static final String GET_MARKETS_BY_EVENT_ID_QUERY = "selectAllByEventId";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String EVENT_COLUMN = "event_id";

    public MySqlMarketDaoImpl(Connection connection) {
        super(connection, Market.class.getSimpleName());
    }

    @Override
    protected Market fillModel(ResultSet resultSet){
        Market market = new Market();
        try {
            market.setId(resultSet.getLong(ID_COLUMN));
            market.setName(MarketNames.valueOf(resultSet.getString(NAME_COLUMN)));
            market.setEventId(resultSet.getLong(EVENT_COLUMN));
            market.setOutcomes(new MySqlOutcomeDaoImpl(connection).getAllByMarketId(resultSet.getLong(ID_COLUMN)));
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return market;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Market market){
        try {
            preparedStatement.setString(1, market.getName().toString());
            preparedStatement.setLong(2, market.getEventId());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Market getById(Long id){
        Market market = super.getById(id);
        market.setOutcomes(new MySqlOutcomeDaoImpl(connection).getAllByMarketId(market.getId()));
        return market;
    }

    @Override
    public Collection<Market> getAll(){
        Collection<Market> markets = super.getAll();
        OutcomeDao outcomeDao = new MySqlOutcomeDaoImpl(connection);
        for (Market market : markets) {
            market.setOutcomes(outcomeDao.getAllByMarketId(market.getId()));
        }
        return markets;
    }

    public Collection<Market> getAllByEventId(Long eventId){
        return super.getAllByConstrain(QUERIES.getString(currentModel + DELIMITER + GET_MARKETS_BY_EVENT_ID_QUERY),
                String.valueOf(eventId));
    }
}