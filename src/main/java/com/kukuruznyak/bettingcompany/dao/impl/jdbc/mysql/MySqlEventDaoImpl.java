package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.MarketDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.event.Market;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.EventBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlEventDaoImpl extends AbstractDaoImpl<Event> implements EventDao {
    private static MySqlEventDaoImpl instance;
    private static TournamentDao tournamentDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getTournamentDao();
    private static UserDao userDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getUserDao();
    private static MarketDao marketDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getMarketDao();

    private static final String GET_EVENTS_BY_BOOKMAKER_ID_QUERY = "selectAllByBookmakerId";
    private static final String GET_EVENTS_BY_STATUS_QUERY = "selectAllStatus";

    private static final String ID_COLUMN = "id";
    private static final String OPEN_DATE_COLUMN = "open_date_and_time";
    private static final String BOOKMAKER_ID_COLUMN = "bookmaker_id";
    private static final String TOURNAMENT_ID_COLUMN = "tournament_id";
    private static final String STATUS_COLUMN = "status";
    private static final String IS_SUSPENDED_COLUMN = "is_suspended";

    public static MySqlEventDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlEventDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlEventDaoImpl();
                }
            }
        }
        return instance;
    }

    private MySqlEventDaoImpl() {
        super(Event.class.getSimpleName());
    }

    @Override
    public Event getById(Long id) throws PersistenceException {
        Event event = super.getById(id);
        Collection<Market> markets = marketDao.getAllByEventId(event.getId());
        event.setMarkets(markets);
        return event;
    }

    @Override
    public Collection<Event> getAll() throws PersistenceException {
        Collection<Event> events = super.getAll();
        for (Event event : events) {
            event.setMarkets(marketDao.getAllByEventId(event.getId()));
        }
        return events;
    }

    @Override
    protected Event fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new EventBuilder()
                    .buildId(resultSet.getLong(ID_COLUMN))
                    .buildDate(resultSet.getDate(OPEN_DATE_COLUMN))
                    .buildBookmaker(userDao.getById(resultSet.getLong(BOOKMAKER_ID_COLUMN)))
                    .buildTournament(tournamentDao.getById(resultSet.getLong(TOURNAMENT_ID_COLUMN)))
                    .buildStatus(EventStatus.valueOf(resultSet.getString(STATUS_COLUMN)))
                    .buildIsSuspended(resultSet.getBoolean(IS_SUSPENDED_COLUMN))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Event event) throws PersistenceException {
        try {
            preparedStatement.setDate(1, new java.sql.Date(event.getCreationDateAndTime().getTime()));
            preparedStatement.setLong(2, event.getTournament().getId());
            preparedStatement.setString(3, event.getStatus().toString());
            preparedStatement.setBoolean(4, event.isSuspended());
            preparedStatement.setLong(5, event.getBookmaker().getId());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public Collection<Event> getAllByBookmakerId(Long bookmakerId) throws PersistenceException {
        Collection<Event> events = super.getAllByConstrain(QUERIES.getString(currentModel + DELIMITER +
                        GET_EVENTS_BY_BOOKMAKER_ID_QUERY),
                String.valueOf(bookmakerId));
        for (Event event : events) {
            event.setMarkets(marketDao.getAllByEventId(event.getId()));
        }
        return events;
    }

    @Override
    public Collection<Event> getAllByStatus(EventStatus eventStatus) {
        Collection<Event> events = super.getAllByConstrain(QUERIES.getString(currentModel + DELIMITER +
                        GET_EVENTS_BY_STATUS_QUERY),
                String.valueOf(eventStatus));
        for (Event event : events) {
            event.setMarkets(marketDao.getAllByEventId(event.getId()));
        }
        return events;
    }
}