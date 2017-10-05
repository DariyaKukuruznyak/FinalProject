package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.util.Collection;
import java.util.List;

public interface TournamentDao extends AbstractDao<Tournament> {
    void addParticipant(Long participantId, Long tournamentId) throws PersistenceException;

    void deleteParticipant(Long participantId, Long tournamentId) throws PersistenceException;

    Collection<Tournament> getTournamentsByParticipant(Long id) throws PersistenceException;
}

