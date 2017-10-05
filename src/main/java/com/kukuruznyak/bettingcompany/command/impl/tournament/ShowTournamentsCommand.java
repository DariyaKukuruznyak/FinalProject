package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

public class ShowTournamentsCommand extends Command {
    private TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Collection<Tournament> tournaments = tournamentService.getActiveTournament();
        request.getSession().setAttribute("tournaments", tournaments);
        return pagesResourceBundle.getString("tournaments");
    }

}
