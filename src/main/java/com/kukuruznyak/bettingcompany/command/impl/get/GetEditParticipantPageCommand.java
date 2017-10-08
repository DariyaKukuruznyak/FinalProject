package com.kukuruznyak.bettingcompany.command.impl.get;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetEditParticipantPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
        request.getSession().setAttribute("activeTournaments", tournamentService.getActiveTournament());
        ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();
        request.getSession().setAttribute("participant", participantService.getById(request.getParameter("participantId")));
        return pagesResourceBundle.getString("editParticipant");
    }
}
