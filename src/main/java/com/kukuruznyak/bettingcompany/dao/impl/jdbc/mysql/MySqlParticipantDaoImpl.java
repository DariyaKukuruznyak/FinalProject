package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.participantbuilder.ParticipantBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlParticipantDaoImpl extends AbstractDaoImpl<Participant> implements ParticipantDao {
    private static MySqlParticipantDaoImpl instance;

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
    protected Participant fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new ParticipantBuilder()
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
}
