package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.TournamentBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateTournamentCommand extends Command {
    private TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
    private ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        try {
            Tournament tournament = fillTournament(request);
            if (tournamentService.isValidParticipant(tournament)) {
                tournamentService.add(tournament);
                request.getSession().setAttribute("successMessage", "Tournament was created successfully");
                LOGGER.error("Tournament was created successfully");
                request.getSession().setAttribute("tournament", tournament);
                request.getSession().setAttribute("participants", participantService.getParticipants());

            } else {
                throw new ApplicationException("Invalid tournament");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute("errorMessage", e.getMessage());
        }
        return pagesResourceBundle.getString("editTournament");
    }

    private Tournament fillTournament(HttpServletRequest request) {
        return new TournamentBuilder()
                .buildName(request.getParameter("name"))
                .buildCountry(request.getParameter("country"))
                .build();
    }
}
