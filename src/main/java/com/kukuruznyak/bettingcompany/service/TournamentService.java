package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;

import java.util.ArrayList;
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
}
