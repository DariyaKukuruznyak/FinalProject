package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Command for editing a tournament
 */
public class EditTournamentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            TournamentService tournamentService = serviceFactory.getTournamentService();
            Tournament tournament = tournamentService.getById(new Long(request.getParameter(TOURNAMENT_ID)));
            tournamentService.fillTournament(request, tournament);
            if (tournamentService.isValidParticipant(tournament)) {
                tournamentService.update(tournament);
                LOGGER.info(StringMessages.getMessage(StringMessages.TOURNAMENT_UPDATED_SUCCESSFULLY));
                currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.TOURNAMENT_UPDATED_SUCCESSFULLY));
                currentSession.setAttribute(TOURNAMENT, tournament);
                ParticipantService participantService = serviceFactory.getParticipantService();
                currentSession.setAttribute(PARTICIPANT, participantService.getParticipants());
            } else {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_TOURNAMENT));
            }
            return pagesResourceBundle.getString(EDIT_TOURNAMENT_PAGE);
        } catch (ApplicationException e) {
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
            LOGGER.error(e.getMessage());
            return pagesResourceBundle.getString(EDIT_TOURNAMENT_PAGE);
        }
    }
}