package com.kukuruznyak.bettingcompany.entity.tournament.builder;

import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;

import java.util.Date;

public class TournamentBuilder {
    private Long id = null;
    private String name;
    private String country;
    private String winner = "";
    private Date beginningDateAndTime = new Date();

    public TournamentBuilder buildId(Long id) {
        this.id = id;
        return this;
    }

    public TournamentBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public TournamentBuilder buildCountry(String country) {
        this.country = country;
        return this;
    }

    public TournamentBuilder buildWinner(String winner) {
        this.winner = winner;
        return this;
    }

    public TournamentBuilder buildBeginningDateAndTime(Date beginningDateAndTime) {
        this.beginningDateAndTime = beginningDateAndTime;
        return this;
    }

    public Tournament build() {
        Tournament tournament = new Tournament();
        tournament.setId(this.id);
        tournament.setName(this.name);
        tournament.setCountry(this.country);
        tournament.setWinner(this.winner);
        tournament.setBeginningDateAndTime(this.beginningDateAndTime);
        return tournament;
    }
}
