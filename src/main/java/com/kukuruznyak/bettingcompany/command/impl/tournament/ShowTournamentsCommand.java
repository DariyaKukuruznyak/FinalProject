package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.service.TournamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Command returns a page of tournaments list
 */
public class ShowTournamentsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TournamentService tournamentService = serviceFactory.getTournamentService();
        Collection<Tournament> tournaments = tournamentService.getActiveTournament();
        request.getSession().setAttribute(TOURNAMENTS, tournaments);
        return pagesResourceBundle.getString(TOURNAMENTS_PAGE);
    }
}
