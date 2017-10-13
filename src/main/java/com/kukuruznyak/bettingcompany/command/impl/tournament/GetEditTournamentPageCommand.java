package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command returns a page for editing a tournament
 */
public class GetEditTournamentPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TournamentService tournamentService = serviceFactory.getTournamentService();
        Tournament tournament = tournamentService.getById(new Long(request.getParameter(TOURNAMENT_ID)));
        HttpSession currentSession = request.getSession();
        currentSession.setAttribute(TOURNAMENT, tournament);
        ParticipantService participantService = serviceFactory.getParticipantService();
        currentSession.setAttribute(PARTICIPANTS, participantService.getParticipants());
        return pagesResourceBundle.getString(EDIT_TOURNAMENT_PAGE);
    }
}
