package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTournamentCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TournamentService tournamentService = serviceFactory.getTournamentService();
        tournamentService.delete(request.getParameter("tournamentId"));
        return new ShowTournamentsCommand().execute(request, response);
    }
}
