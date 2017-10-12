package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.TournamentBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class MySqlTournamentDaoImpl extends AbstractDaoImpl<Tournament> implements TournamentDao {
    private static final String LINKED_TABLE_QUERY = "ParticipantLinkTournament";
    private static final String ALL_TOURNAMENT_BY_PARTICIPANTS_QUERY = "getTournaments";
    private static final String ALL_ACTIVE_TOURNAMENTS_QUERY = "selectActiveTournaments";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String START_DATE_COLUMN = "start_date_and_time";
    private static final String WINNER_COLUMN = "winner";

    public MySqlTournamentDaoImpl(Connection connection) {
        super(connection, Tournament.class.getSimpleName());
    }

    @Override
    public void addParticipant(Long participantId, Long tournamentId) throws PersistenceException {
        new MySqlParticipantDaoImpl(connection).addTournament(participantId, tournamentId);
    }

    @Override
    public void deleteParticipant(Long participantId, Long tournamentId) throws PersistenceException {
        new MySqlParticipantDaoImpl(connection).deleteTournament(participantId, tournamentId);
    }

    @Override
    public Collection<Tournament> getTournamentsByParticipant(Long id) throws PersistenceException {
        return super.getAllByConstrain(
                QUERIES.getString(LINKED_TABLE_QUERY + DELIMITER + ALL_TOURNAMENT_BY_PARTICIPANTS_QUERY),
                String.valueOf(id));
    }

    @Override
    public Collection<Tournament> getActiveTournaments() throws PersistenceException {
        Collection<Tournament> tournaments = super.getAllByConstrain(
                QUERIES.getString(currentModel + DELIMITER + ALL_ACTIVE_TOURNAMENTS_QUERY),
                String.valueOf(new java.sql.Date(new Date().getTime())));
        ParticipantDao participantDao = new MySqlParticipantDaoImpl(connection);
        for (Tournament tournament : tournaments) {
            tournament.setParticipants(participantDao.getParticipantsByTournament(tournament.getId()));
        }
        return tournaments;
    }

    @Override
    protected Tournament fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new TournamentBuilder()
                    .buildId(resultSet.getLong(ID_COLUMN))
                    .buildName(resultSet.getString(NAME_COLUMN))
                    .buildBeginningDateAndTime(resultSet.getDate(START_DATE_COLUMN))
                    .buildWinner(resultSet.getString(WINNER_COLUMN))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Tournament tournament) throws PersistenceException {
        try {
            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setDate(2, new java.sql.Date(tournament.getBeginningDateAndTime().getTime()));
            preparedStatement.setString(3, tournament.getWinner());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}