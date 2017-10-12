package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.util.Collection;
import java.util.List;

public interface ParticipantDao extends AbstractDao<Participant> {
    void addTournament(Long participantId, Long tournamentId);

    void deleteTournament(Long participantId, Long tournamentId) ;

    Collection<Participant> getParticipantsByTournament(Long id);
}
