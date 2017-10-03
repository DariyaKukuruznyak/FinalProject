package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlTournamentDaoImpl extends AbstractDaoImpl<Tournament> implements TournamentDao {
    private static MySqlTournamentDaoImpl instance;
    private static ParticipantDao participantDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getParticipantDao();
    private static final String LINKED_TABLE_QUERY = "ParticipantLinkTournament";
    private static final String ALL_TOURNAMENT_BY_PARTICIPANTS = "getTournaments";

    public static MySqlTournamentDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlTournamentDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlTournamentDaoImpl();
                    LOGGER.info("Instance of " + MySqlTournamentDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlTournamentDaoImpl() {
        super(Tournament.class.getSimpleName());
    }

    @Override
    protected Tournament fillModel(ResultSet resultSet) throws PersistenceException {
        Tournament tournament = new Tournament();
        try {
            tournament.setId(resultSet.getLong("id"));
            tournament.setName(resultSet.getString("name"));
            tournament.setCountry(resultSet.getString("country"));
            tournament.setBeginningDateAndTime(resultSet.getDate("start_date_and_time"));//TODO
            tournament.setScore(resultSet.getString("score"));
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return tournament;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Tournament tournament) throws PersistenceException {
        try {
            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setString(2, tournament.getCountry());
            preparedStatement.setDate(3, new java.sql.Date(tournament.getBeginningDateAndTime().getTime()));
            preparedStatement.setString(4, tournament.getScore());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void addParticipant(Long participantId, Long tournamentId) throws PersistenceException {
        participantDao.addTournament(participantId, tournamentId);
    }

    @Override
    public void deleteParticipant(Long participantId, Long tournamentId) throws PersistenceException {
        participantDao.deleteTournament(participantId, tournamentId);
    }

    @Override
    public List<Tournament> getParticipants(Long id) throws PersistenceException {
        return super.getAllByConstrain(
                QUERIES.getString(LINKED_TABLE_QUERY + "." + ALL_TOURNAMENT_BY_PARTICIPANTS),
                String.valueOf(id));
    }
}