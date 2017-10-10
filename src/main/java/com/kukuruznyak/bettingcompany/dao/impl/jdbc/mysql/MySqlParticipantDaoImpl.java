package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.ParticipantBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlParticipantDaoImpl extends AbstractDaoImpl<Participant> implements ParticipantDao {
    private static MySqlParticipantDaoImpl instance;

    private static final String LINKED_TABLE_QUERY = "ParticipantLinkTournament";
    private static final String ADD_TOURNAMENT_QUERY = "addLink";
    private static final String DELETE_TOURNAMENT_QUERY = "deleteLink";
    private static final String ALL_PARTICIPANTS_BY_TOURNAMENT_QUERY = "getParticipants";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String AGE_COLUMN = "age";
    private static final String WEIGHT_COLUMN = "weight";
    private static final String TRAINER_COLUMN = "trainer";
    private static final String JOCKEY_COLUMN = "jockey";

    public static MySqlParticipantDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlParticipantDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlParticipantDaoImpl();
                }
            }
        }
        return instance;
    }

    private MySqlParticipantDaoImpl() {
        super(Participant.class.getSimpleName());
    }

    @Override
    public void addTournament(Long participantId, Long tournamentId) {
        updateLinkedTable(participantId, tournamentId, QUERIES.getString(LINKED_TABLE_QUERY + DELIMITER + ADD_TOURNAMENT_QUERY));
    }

    @Override
    public void deleteTournament(Long participantId, Long tournamentId) {
        updateLinkedTable(participantId, tournamentId, QUERIES.getString(LINKED_TABLE_QUERY + DELIMITER + DELETE_TOURNAMENT_QUERY));
    }

    @Override
    public Collection<Participant> getParticipantsByTournament(Long id) throws PersistenceException {
        return super.getAllByConstrain(
                QUERIES.getString(LINKED_TABLE_QUERY + DELIMITER + ALL_PARTICIPANTS_BY_TOURNAMENT_QUERY),
                String.valueOf(id));
    }

    @Override
    protected Participant fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new ParticipantBuilder()
                    .buildId(resultSet.getLong(ID_COLUMN))
                    .buildName(resultSet.getString(NAME_COLUMN))
                    .buildAge(resultSet.getInt(AGE_COLUMN))
                    .buildWeight(resultSet.getInt(WEIGHT_COLUMN))
                    .buildTrainer(resultSet.getString(TRAINER_COLUMN))
                    .buildJockey(resultSet.getString(JOCKEY_COLUMN))
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
        } catch (SQLException e) {
            LOGGER.error(LINKED_TABLE_DB_ERROR + e.getMessage());
            throw new PersistenceException(e.getMessage());
        }
    }
}
