package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IncludeParticipantToTournamentCommand extends Command {
    private TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
    private ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        String participantId = request.getParameter("participantId");
        String tournamentId = request.getParameter("tournamentId");
        String command = request.getParameter("command");
        if (command.equals("includeParticipant")) {
            tournamentService.includeParticipant(participantId, tournamentId);
        }
        if (command.equals("excludeParticipant")) {
            tournamentService.excludeParticipant(participantId, tournamentId);
        }
        String editedModel = request.getParameter("editedModel");
        switch (editedModel) {
            case "participant":
                request.getSession().setAttribute("participant", participantService.getById(participantId));
                request.getSession().setAttribute("tournaments", tournamentService.getActiveTournament());
                return pagesResourceBundle.getString("editParticipant");
            case "tournament":
                request.getSession().setAttribute("tournament", tournamentService.getById(tournamentId));
                request.getSession().setAttribute("participants", participantService.getParticipants());
                return pagesResourceBundle.getString("editTournament");
            default:
                throw new ApplicationException("Unexpected request!");
        }
    }
}
