package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class TournamentService extends AbstractService {
    private static TournamentService instance;

    public static TournamentService getInstance() {
        if (instance == null) {
            synchronized (TournamentService.class) {
                if (instance == null) {
                    instance = new TournamentService();
                }
            }
        }
        return instance;
    }

    private TournamentService() {
    }

    public Collection<Tournament> getActiveTournament() {
        try {
            try (Connection connection = dataSource.getConnection()) {
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                Collection<Tournament> tournaments = tournamentDao.getActiveTournaments();
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                for (Tournament tournament : tournaments) {
                    tournament.setParticipants(participantDao.getParticipantsByTournament(tournament.getId()));
                }
                return tournaments;
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void add(Tournament tournament) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                tournamentDao.add(tournament);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Tournament getById(Long tournamentId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                Tournament tournament = tournamentDao.getById(new Long(tournamentId));
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                tournament.setParticipants(participantDao.getParticipantsByTournament(tournament.getId()));
                return tournament;
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void update(Tournament tournament) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                tournamentDao.update(tournament);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(String tournamentId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                tournamentDao.delete(new Long(tournamentId));
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void excludeParticipant(String participantId, String tournamentId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                tournamentDao.deleteParticipant(new Long(participantId), new Long(tournamentId));
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void includeParticipant(String participantId, String tournamentId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
//                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
//                tournamentDao.addParticipant(new Long(participantId), new Long(tournamentId));
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                participantDao.addTournament(new Long(participantId), new Long(tournamentId));
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public boolean isValidParticipant(Tournament tournament) {
        String pattern = "([A-Za-zА-Яа-я'ЇїІі ]{1,20})";
        if (!tournament.getName().matches(pattern)) {
            tournament.setName("");
            return false;
        }
        return true;
    }
}