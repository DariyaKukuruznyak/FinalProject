package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.EventBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlEventDaoImpl extends AbstractDaoImpl<Event> implements EventDao {
    private static final String GET_EVENTS_BY_BOOKMAKER_ID_QUERY = "selectAllByBookmakerId";
    private static final String GET_EVENTS_BY_STATUS_QUERY = "selectAllStatus";
    private static final String GET_EVENT_BY_BET_ITEM_ID_QUERY = "selectByBetItemId";

    private static final String ID_COLUMN = "id";
    private static final String OPEN_DATE_COLUMN = "open_date";
    private static final String BOOKMAKER_ID_COLUMN = "bookmaker_id";
    private static final String TOURNAMENT_ID_COLUMN = "tournament_id";
    private static final String STATUS_COLUMN = "status";
    private static final String IS_SUSPENDED_COLUMN = "is_suspended";
    private static final String TURNOVER_COLUMN = "turnover";
    private static final String PROFIT_COLUMN = "profit";

    public MySqlEventDaoImpl(Connection connection) {
        super(connection, Event.class.getSimpleName());
    }

    @Override
    public Collection<Event> getAllByBookmakerId(Long bookmakerId) {
        Collection<Event> events = super.getAllByConstrain(QUERIES.getString(currentModel + DELIMITER +
                        GET_EVENTS_BY_BOOKMAKER_ID_QUERY),
                String.valueOf(bookmakerId));
        return events;
    }

    @Override
    public Collection<Event> getAllByStatus(EventStatus eventStatus) {
        return super.getAllByConstrain(QUERIES.getString(currentModel + DELIMITER +
                        GET_EVENTS_BY_STATUS_QUERY),
                String.valueOf(eventStatus));
    }

    @Override
    public Event getByBetItemId(Long betItemId) {
        return super.getByConstrain(currentModel + DELIMITER + GET_EVENT_BY_BET_ITEM_ID_QUERY, betItemId);
    }

    @Override
    protected Event fillModel(ResultSet resultSet) {
        try {
            return new EventBuilder()
                    .buildId(resultSet.getLong(ID_COLUMN))
                    .buildDate(resultSet.getDate(OPEN_DATE_COLUMN))
                    .buildBookmaker(new MySqlUserDaoImpl(connection).getById(resultSet.getLong(BOOKMAKER_ID_COLUMN)))
                    .buildTournament(new MySqlTournamentDaoImpl(connection).getById(resultSet.getLong(TOURNAMENT_ID_COLUMN)))
                    .buildStatus(EventStatus.valueOf(resultSet.getString(STATUS_COLUMN)))
                    .buildIsSuspended(resultSet.getBoolean(IS_SUSPENDED_COLUMN))
                    .buildTurnover(resultSet.getBigDecimal(TURNOVER_COLUMN))
                    .buildProfit(resultSet.getBigDecimal(PROFIT_COLUMN))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Event event) {
        try {
            preparedStatement.setDate(1, new java.sql.Date(event.getCreationDate().getTime()));
            preparedStatement.setLong(2, event.getTournament().getId());
            preparedStatement.setString(3, event.getStatus().toString());
            preparedStatement.setBoolean(4, event.isSuspended());
            preparedStatement.setLong(5, event.getBookmaker().getId());
            preparedStatement.setBigDecimal(6, event.getTurnover());
            preparedStatement.setBigDecimal(7, event.getProfit());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}