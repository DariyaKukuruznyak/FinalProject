package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class ParticipantService extends AbstractService {
    private static volatile ParticipantService instance;

    public static ParticipantService getInstance() {
        if (instance == null) {
            synchronized (ParticipantService.class) {
                if (instance == null) {
                    instance = new ParticipantService();
                }
            }
        }
        return instance;
    }

    private ParticipantService() {
    }

    public Collection<Participant> getParticipants() {
        try {
            try (Connection connection = dataSource.getConnection()) {
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                Collection<Participant> participants = participantDao.getAll();
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                for (Participant participant : participants) {
                    participant.setTournaments(tournamentDao.getTournamentsByParticipant(participant.getId()));
                }
                return participants;
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Participant add(Participant participant) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                return participantDao.add(participant);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(Long participantId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                participantDao.delete(participantId);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void update(Participant participant) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                participantDao.update(participant);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Participant getById(Long participantId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                Participant participant = participantDao.getById(participantId);
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                participant.setTournaments(tournamentDao.getTournamentsByParticipant(participant.getId()));
                return participant;
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Collection<Participant> getByTournamentId(Long tournamentId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                Collection<Participant> participants = participantDao.getParticipantsByTournament(tournamentId);
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                for (Participant participant : participants) {
                    participant.setTournaments(tournamentDao.getTournamentsByParticipant(participant.getId()));
                }
                return participants;

            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public boolean isValidParticipant(Participant participant) {
        String pattern = "([A-Za-zА-Яа-я'ЇїІі ]{1,20})";
        if (!participant.getName().matches(pattern)) {
            participant.setName("");
            return false;
        }
        if (!participant.getTrainer().matches(pattern)) {
            participant.setTrainer("");
            return false;
        }
        if (!participant.getJockey().matches(pattern)) {
            participant.setJockey("");
            return false;
        }
        return true;
    }
}