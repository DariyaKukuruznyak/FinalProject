package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.service.TournamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for selecting tournament during creation of event
 */
public class SelectTournamentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TournamentService tournamentService = serviceFactory.getTournamentService();
        Tournament selectedTournament = tournamentService.getById(new Long(request.getParameter(TOURNAMENT_ID)));
        request.getSession().setAttribute(SELECTED_TOURNAMENT, selectedTournament);
        return pagesResourceBundle.getString(ADD_EVENT_PAGE);
    }
}
