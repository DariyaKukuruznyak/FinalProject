package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectTournamentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
        Tournament selectedTournament = tournamentService.getById(request.getParameter("tournamentId"));
        request.getSession().setAttribute("selectedTournament", selectedTournament);
        return pagesResourceBundle.getString("addEvent");
    }
}
