package com.kukuruznyak.bettingcompany.service;

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
                return tournamentDao.getActiveTournaments();
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public boolean isValidParticipant(Tournament tournament) {
        return true;
    }//T

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

    public Tournament getById(String tournamentId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                return tournamentDao.getById(new Long(tournamentId));
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
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                tournamentDao.addParticipant(new Long(participantId), new Long(tournamentId));
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}