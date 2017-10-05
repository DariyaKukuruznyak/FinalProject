package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.ParticipantBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MySqlParticipantDaoImpl extends AbstractDaoImpl<Participant> implements ParticipantDao {
    private static TournamentDao tournamentDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getTournamentDao();
    private static MySqlParticipantDaoImpl instance;
    private static final String LINKED_TABLE_QUERY = "ParticipantLinkTournament";
    private static final String ADD_TOURNAMENT = "addLink";
    private static final String DELETE_TOURNAMENT = "deleteLink";
    private static final String ALL_PARTICIPANTS_BY_TOURNAMENT = "getParticipants";

    public static MySqlParticipantDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlParticipantDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlParticipantDaoImpl();
                    LOGGER.info("Instance of " + MySqlParticipantDaoImpl.class.getSimpleName() + " was created");
                }
            }
        }
        return instance;
    }

    private MySqlParticipantDaoImpl() {
        super(Participant.class.getSimpleName());
    }

    @Override
    public Participant getById(Long id) throws PersistenceException {
        Participant participant = super.getById(id);
        Collection<Tournament> tournaments = tournamentDao.getTournamentsByParticipant(participant.getId());
        participant.setTournaments(tournaments);
        return participant;
    }

    @Override
    public Collection<Participant> getAll() throws PersistenceException {
        Collection<Participant> participants = super.getAll();
        for (Participant participant : participants) {
            participant.setTournaments(tournamentDao.getTournamentsByParticipant(participant.getId()));
        }
        return participants;
    }

    @Override
    public void addTournament(Long participantId, Long tournamentId) {
        updateLinkedTable(participantId, tournamentId, QUERIES.getString(LINKED_TABLE_QUERY + "." + ADD_TOURNAMENT));
    }

    @Override
    public void deleteTournament(Long participantId, Long tournamentId) {
        updateLinkedTable(participantId, tournamentId, QUERIES.getString(LINKED_TABLE_QUERY + "." + DELETE_TOURNAMENT));
    }

    @Override
    public Collection<Participant> getParticipantsByTournament(Long id) throws PersistenceException {
        return super.getAllByConstrain(
                QUERIES.getString(LINKED_TABLE_QUERY + "." + ALL_PARTICIPANTS_BY_TOURNAMENT),
                String.valueOf(id));
    }

    @Override
    protected Participant fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new ParticipantBuilder()
                    .buildId(resultSet.getLong("id"))
                    .buildName(resultSet.getString("name"))
                    .buildAge(resultSet.getInt("age"))
                    .buildWeight(resultSet.getInt("weight"))
                    .buildTrainer(resultSet.getString("trainer"))
                    .buildJockey(resultSet.getString("jockey"))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Participant participant) throws PersistenceException {
        try {
            preparedStatement.setString(1, participant.getName());
            preparedStatement.setString(2, String.valueOf(participant.getAge()));
            preparedStatement.setString(3, String.valueOf(participant.getWeight()));
            preparedStatement.setString(4, participant.getTrainer());
            preparedStatement.setString(5, participant.getJockey());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    private void updateLinkedTable(Long participantId, Long tournamentId, String query) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, participantId);
            preparedStatement.setLong(2, tournamentId);
            preparedStatement.executeUpdate();
            LOGGER.info("Database error during action with linked table");
        } catch (SQLException e) {
            LOGGER.error("Database error during action with linked table with message: " + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
    }
}
