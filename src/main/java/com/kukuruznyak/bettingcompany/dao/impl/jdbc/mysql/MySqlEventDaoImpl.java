package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.EventDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.dao.UserDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
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

    private static final String GET_EVENTS_BY_BOOKMAKER_ID = "selectAllByBookmakerId";

    public static MySqlEventDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlEventDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlEventDaoImpl();
                    LOGGER.info("Instance of " + MySqlEventDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlEventDaoImpl() {
        super(Event.class.getSimpleName());
    }

    @Override
    protected Event fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new EventBuilder()
                    .buildId(resultSet.getLong("id"))
                    .buildDate(resultSet.getDate("open_date_and_time"))
                    .buildTournament(tournamentDao.getById(resultSet.getLong("tournament_id")))
                    .buildBookmaker(userDao.getById(resultSet.getLong("bookmaker_id")))
                    .buildStatus(EventStatus.valueOf(resultSet.getString("status")))
                    .buildIsSuspended(resultSet.getBoolean("is_suspended"))
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
        return super.getAllByConstrain(QUERIES.getString(currentModel + "." + GET_EVENTS_BY_BOOKMAKER_ID),
                String.valueOf(bookmakerId));
    }
}