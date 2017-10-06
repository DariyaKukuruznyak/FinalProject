package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;

import java.util.Collection;
import java.util.List;

public class ParticipantService extends AbstractService {
    private static ParticipantService instance;
    private ParticipantDao participantDao = daoFactory.getParticipantDao();

    public static ParticipantService getInstance() {
        if (instance == null) {
            synchronized (ParticipantService.class) {
                if (instance == null) {
                    instance = new ParticipantService();
                }
            }
        }
        return instance;
    }

    private ParticipantService() {
    }

    public Collection<Participant> getParticipants() {
        return participantDao.getAll();
    }

    public boolean isValidParticipant(Participant participant) {
        if (!participant.getName().matches(validationBundle.getString("name"))) {
            participant.setName("");
            return false;
        }
        if (!participant.getTrainer().matches(validationBundle.getString("name"))) {
            participant.setTrainer("");
            return false;
        }
        if (!participant.getJockey().matches(validationBundle.getString("name"))) {
            participant.setJockey("");
            return false;
        }
        return true;
    }

    public Participant add(Participant participant) {
       return participantDao.add(participant);
    }

    public void delete(String participantId) {
        participantDao.delete(new Long(participantId));
    }

    public Participant getParticipantById(String participantId) {
        return participantDao.getById(new Long(participantId));
    }

    public void update(Participant participant) {
        participantDao.update(participant);
    }

    public Participant getById(String participantId) {
        return participantDao.getById(new Long(participantId));
    }
}
