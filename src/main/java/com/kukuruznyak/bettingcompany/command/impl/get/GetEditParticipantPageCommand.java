package com.kukuruznyak.bettingcompany.command.impl.get;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetEditParticipantPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
        HttpSession currentSession = request.getSession();
        currentSession.setAttribute("activeTournaments", tournamentService.getActiveTournament());
        ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();
        currentSession.setAttribute("participant", participantService.getById(request.getParameter("participantId")));
        return pagesResourceBundle.getString("editParticipant");
    }
}
