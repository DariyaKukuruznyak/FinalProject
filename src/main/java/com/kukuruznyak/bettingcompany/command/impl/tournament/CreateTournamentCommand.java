package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.TournamentBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateTournamentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            Tournament tournament = fillTournament(request);
            TournamentService tournamentService = serviceFactory.getTournamentService();
            if (tournamentService.isValidParticipant(tournament)) {
                tournamentService.add(tournament);
                currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.TOURNAMENT_CREATED_SUCCESSFULLY));
                LOGGER.error(StringMessages.getMessage(StringMessages.TOURNAMENT_CREATED_SUCCESSFULLY));
                request.getSession().setAttribute(TOURNAMENT, tournament);
                ParticipantService participantService = serviceFactory.getParticipantService();
                currentSession.setAttribute(PARTICIPANTS, participantService.getParticipants());
            } else {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_TOURNAMENT));
            }
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
        }
        return pagesResourceBundle.getString(EDIT_TOURNAMENT_PAGE);
    }

    private Tournament fillTournament(HttpServletRequest request) {
        return new TournamentBuilder()
                .buildName(request.getParameter(NAME))
                .buildCountry(request.getParameter(COUNTRY))
                .build();
    }
}