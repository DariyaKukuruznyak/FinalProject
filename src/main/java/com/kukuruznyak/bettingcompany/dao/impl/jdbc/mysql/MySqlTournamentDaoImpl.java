package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.TournamentBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    public Tournament getById(Long id) throws PersistenceException {
        Tournament tournament = super.getById(id);
        Collection<Participant> participants = participantDao.getParticipantsByTournament(tournament.getId());
        tournament.setParticipants(participants);
        return tournament;
    }

    @Override
    public Collection<Tournament> getAll() throws PersistenceException {
        Collection<Tournament> tournaments = super.getAll();
        for (Tournament tournament : tournaments) {
            tournament.setParticipants(participantDao.getParticipantsByTournament(tournament.getId()));
        }
        return tournaments;
    }

    @Override
    protected Tournament fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new TournamentBuilder()
                    .buildId(resultSet.getLong("id"))
                    .buildName(resultSet.getString("name"))
                    .buildCountry(resultSet.getString("country"))
                    .buildBeginningDateAndTime(resultSet.getDate("start_date_and_time"))//TODO
                    .buildScore(resultSet.getString("score"))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
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
    public Collection<Tournament> getTournamentsByParticipant(Long id) throws PersistenceException {
        return super.getAllByConstrain(
                QUERIES.getString(LINKED_TABLE_QUERY + "." + ALL_TOURNAMENT_BY_PARTICIPANTS),
                String.valueOf(id));
    }
}