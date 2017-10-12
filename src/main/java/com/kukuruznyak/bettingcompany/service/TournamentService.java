package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.exception.ServiceException;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import static com.kukuruznyak.bettingcompany.command.RequestAttributeConstants.*;

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

    public Tournament add(Tournament tournament) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                TournamentDao tournamentDao = daoFactory.getTournamentDao(connection);
                return tournamentDao.add(tournament);
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
                ParticipantDao participantDao = daoFactory.getParticipantDao(connection);
                participantDao.addTournament(new Long(participantId), new Long(tournamentId));
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Tournament fillTournament(HttpServletRequest request, Tournament tournament) {
        String name = request.getParameter(NAME);
        if (name != null) {
            tournament.setName(name);
        }
        String date = request.getParameter(BEGINNING_DATE);
        if (date != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                tournament.setBeginningDate(format.parse(date));
            } catch (ParseException e) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_INPUT));
            }
        }
        String winner = request.getParameter(WINNER);
        if (winner != null) {
            tournament.setWinner(winner);
        }
        return tournament;
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