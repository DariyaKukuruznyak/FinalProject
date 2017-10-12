package com.kukuruznyak.bettingcompany.command.impl.participant;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.RequestAttributeConstants;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetEditParticipantPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TournamentService tournamentService = serviceFactory.getTournamentService();
        HttpSession currentSession = request.getSession();
        currentSession.setAttribute(ACTIVE_TOURNAMENTS, tournamentService.getActiveTournament());
        ParticipantService participantService = serviceFactory.getParticipantService();
        currentSession.setAttribute(PARTICIPANT, participantService.getById(new Long(request.getParameter(PARTICIPANT_ID))));
        return pagesResourceBundle.getString(EDIT_PARTICIPANT_PAGE);
    }
}
