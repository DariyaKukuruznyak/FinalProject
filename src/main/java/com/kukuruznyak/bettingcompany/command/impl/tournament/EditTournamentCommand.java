package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditTournamentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        try {
            TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
            Tournament tournament = tournamentService.getById(request.getParameter("tournamentId"));
            editTournament(request, tournament);
            if (tournamentService.isValidParticipant(tournament)) {
                tournamentService.update(tournament);
                LOGGER.info("Tournament with id = " + tournament.getId() + " updated.");
                request.setAttribute("successMessage", "Tournament was updated successfully.");
                request.getSession().setAttribute("tournament", tournament);
                ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();
                request.getSession().setAttribute("participant", participantService.getParticipants());
            } else {
                throw new ApplicationException("Incorrect tournament!");
            }
        } catch (ApplicationException e) {
            request.getSession().setAttribute("errorMessage", e.getMessage());
            LOGGER.error(e.getMessage());
        } finally {
            return pagesResourceBundle.getString("editTournament");
        }
    }

    private void editTournament(HttpServletRequest request, Tournament tournament) {
        tournament.setName(request.getParameter("name"));
        tournament.setCountry(request.getParameter("country"));
        tournament.setWinner(request.getParameter("winner"));
    }
}