package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.entity.tournament.Country;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.tournament.participantbuilder.ParticipantBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
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

    public List<Country> countriesWithActiveTournaments() {
//        return Collections.emptyList();
        Country country = new Country();
        country.setName("USA");
        Tournament tournament = new Tournament();
        tournament.setName("USA Races");
        country.addTournament(tournament);
        country.addTournament(tournament);
        country.addTournament(tournament);
        List<Country> countryList = new ArrayList<>();
        countryList.add(country);
        countryList.add(country);
        countryList.add(country);
        return countryList;
    }

    public List<Tournament> getActiveTournament() {
        Tournament tournament = new Tournament();
        tournament.setName("Race");
        Country country = new Country();
        country.setName("England");
        tournament.setCountry(country);
        List<Tournament> tournamentList = new ArrayList<>();
        tournamentList.add(tournament);
        tournamentList.add(tournament);
        tournamentList.add(tournament);
        tournamentList.add(tournament);
        return tournamentList;
    }
}
