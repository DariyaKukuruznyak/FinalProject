package com.kukuruznyak.bettingcompany.entity.tournament;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.ArrayList;
import java.util.List;

public class Country extends Model {
    private String name;
    private List<Tournament> tournaments;

    public Country() {
    }

    public Country(Long id) {
        super(id);
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public int getTournamentNumber() {
        return this.tournaments.size();
    }

    public void addTournament(Tournament tournament) {
        if (this.tournaments == null) {
            this.tournaments = new ArrayList<>();
        }
        this.tournaments.add(tournament);
    }
}
