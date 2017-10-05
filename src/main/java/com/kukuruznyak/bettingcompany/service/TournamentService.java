package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;

import java.util.List;

public class TournamentService extends AbstractService {
    private static TournamentService instance;
    private TournamentDao tournamentDao = daoFactory.getTournamentDao();

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

    public List<Tournament> getActiveTournament() {
        return tournamentDao.getAll(); //TODO
    }

    public boolean isValidParticipant(Tournament tournament) {
        return true;
    }

    public void add(Tournament tournament) {
        tournamentDao.add(tournament);
    }

    public Tournament getById(String tournamentId) {
        return tournamentDao.getById(new Long(tournamentId));
    }

    public void update(Tournament tournament) {
        tournamentDao.update(tournament);
    }

    public void delete(String tournamentId) {
        tournamentDao.delete(new Long(tournamentId));
    }

    public void excludeParticipant(String participantId, String tournamentId) {
        tournamentDao.deleteParticipant(new Long(participantId), new Long(tournamentId));
    }

    public void includeParticipant(String participantId, String tournamentId) {
        tournamentDao.addParticipant(new Long(participantId), new Long(tournamentId));
    }
}
