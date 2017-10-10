package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.RequestAttributeConstants;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MoveParticipantInTournamentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String participantId = request.getParameter(PARTICIPANT_ID);
        String tournamentId = request.getParameter(TOURNAMENT_ID);
        String action = request.getParameter(ACTION);
        TournamentService tournamentService = serviceFactory.getTournamentService();
        if (action.equals(INCLUDE_ACTION)) {
            tournamentService.includeParticipant(participantId, tournamentId);
        }
        if (action.equals(EXCLUDE_ACTION)) {
            tournamentService.excludeParticipant(participantId, tournamentId);
        }
        String editedModel = request.getParameter(EDITED_MODEL);
        ParticipantService participantService = serviceFactory.getParticipantService();
        HttpSession currentSession = request.getSession();
        switch (editedModel) {
            case PARTICIPANT:
                currentSession.setAttribute(PARTICIPANT, participantService.getById(participantId));
                currentSession.setAttribute(TOURNAMENTS, tournamentService.getActiveTournament());
                return pagesResourceBundle.getString(EDIT_PARTICIPANT_PAGE);
            case TOURNAMENT:
                currentSession.setAttribute(TOURNAMENT, tournamentService.getById(tournamentId));
                currentSession.setAttribute(PARTICIPANTS, participantService.getParticipants());
                return pagesResourceBundle.getString(EDIT_TOURNAMENT_PAGE);
            default:
                throw new ApplicationException(UNEXPECTED_REQUEST);
        }
    }
}
