package com.kukuruznyak.bettingcompany.entity.tournament.builder;

import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;

import java.util.Collection;
import java.util.Date;

public class TournamentBuilder {
    private Long id = null;
    private String name;
    private String country;
    private String winner = "";
    private Date beginningDateAndTime = new Date();
    private Collection<Participant> participants;
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

    public TournamentBuilder buildParticipants(Collection<Participant> participants) {
        this.participants = participants;
        return this;
    }

    public Tournament build() {
        Tournament tournament = new Tournament();
        tournament.setId(this.id);
        tournament.setName(this.name);
        tournament.setCountry(this.country);
        tournament.setWinner(this.winner);
        tournament.setBeginningDateAndTime(this.beginningDateAndTime);
        tournament.setParticipants(this.participants);
        return tournament;
    }
}
