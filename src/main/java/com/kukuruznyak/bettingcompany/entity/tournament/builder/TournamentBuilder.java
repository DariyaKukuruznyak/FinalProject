package com.kukuruznyak.bettingcompany.entity.tournament.builder;

import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;

import java.util.Collection;
import java.util.Date;

public class TournamentBuilder {
    private Long id = null;
    private String name;
    private String winner = "";
    private Date beginningDate = new Date();
    private Collection<Participant> participants;

    public TournamentBuilder buildId(Long id) {
        this.id = id;
        return this;
    }

    public TournamentBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public TournamentBuilder buildWinner(String winner) {
        this.winner = winner;
        return this;
    }

    public TournamentBuilder buildBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
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
        tournament.setWinner(this.winner);
        tournament.setBeginningDate(this.beginningDate);
        tournament.setParticipants(this.participants);
        return tournament;
    }
}
