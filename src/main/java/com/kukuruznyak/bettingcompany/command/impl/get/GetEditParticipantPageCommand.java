package com.kukuruznyak.bettingcompany.command.impl.get;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetEditParticipantPageCommand extends Command {
    TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
    private ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        request.getSession().setAttribute("activeTournaments", tournamentService.getActiveTournament());
        request.getSession().setAttribute("participant", participantService.getById(request.getParameter("participantId")));
        return pagesResourceBundle.getString("editParticipant");
    }
}
