package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.TournamentBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Command for creation a new tournament
 */
public class CreateTournamentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            TournamentService tournamentService = serviceFactory.getTournamentService();
            Tournament tournament = tournamentService.fillTournament(request, new TournamentBuilder().build());
            if (tournamentService.isValidParticipant(tournament)) {
                tournament = tournamentService.add(tournament);
                currentSession.setAttribute(TOURNAMENT, tournament);
                Collection<Tournament> tournaments = tournamentService.getActiveTournament();
                currentSession.setAttribute(TOURNAMENTS, tournaments);
                ParticipantService participantService = serviceFactory.getParticipantService();
                Collection<Participant> participants = participantService.getParticipants();
                currentSession.setAttribute(PARTICIPANTS, participants);
                currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.TOURNAMENT_CREATED_SUCCESSFULLY));
                return pagesResourceBundle.getString(EDIT_TOURNAMENT_PAGE);
            } else {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_TOURNAMENT));
            }
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
            return pagesResourceBundle.getString(ADD_TOURNAMENT_PAGE);
        }
    }
}