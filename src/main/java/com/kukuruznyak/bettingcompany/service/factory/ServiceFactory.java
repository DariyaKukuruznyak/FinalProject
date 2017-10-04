package com.kukuruznyak.bettingcompany.service.factory;

import com.kukuruznyak.bettingcompany.service.*;

public class ServiceFactory {
    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (ServiceFactory.class) {
                if (instance == null) {
                    instance = new ServiceFactory();
                }
            }
        }
        return instance;
    }

    private ServiceFactory() {
    }

    public UserService getUserService() {
        return UserService.getInstance();
    }

    public BetService getBetService() {
        return BetService.getInstance();
    }

    public EventService getEventService() {
        return EventService.getInstance();
    }

    public ParticipantService getParticipantService() {
        return ParticipantService.getInstance();
    }

    public TournamentService getTournamentService() {
        return TournamentService.getInstance();
    }

    public ClientService getClientService() {
        return ClientService.getInstance();
    }
}
