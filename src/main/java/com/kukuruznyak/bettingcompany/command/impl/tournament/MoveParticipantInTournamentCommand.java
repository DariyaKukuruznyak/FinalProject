package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveParticipantInTournamentCommand extends Command {
    private TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
    private ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();

    private static final String INCLUDE_ACTION = "include";
    private static final String EXCLUDE_ACTION = "exclude";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        String participantId = request.getParameter("participantId");
        String tournamentId = request.getParameter("tournamentId");
        String action = request.getParameter("action");
        if (action.equals(INCLUDE_ACTION)) {
            tournamentService.includeParticipant(participantId, tournamentId);
        }
        if (action.equals(EXCLUDE_ACTION)) {
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