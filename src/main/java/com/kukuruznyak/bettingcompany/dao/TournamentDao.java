package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;

import java.util.Collection;

public interface TournamentDao extends AbstractDao<Tournament> {
    void addParticipant(Long participantId, Long tournamentId);

    void deleteParticipant(Long participantId, Long tournamentId);

    Collection<Tournament> getTournamentsByParticipant(Long id);

    Collection<Tournament> getActiveTournaments();
}

