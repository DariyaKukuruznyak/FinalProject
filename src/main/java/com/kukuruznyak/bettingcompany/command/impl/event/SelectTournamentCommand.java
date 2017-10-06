package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class SelectTournamentCommand extends Command {
    private TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Tournament selectedTournament = tournamentService.getById(request.getParameter("tournamentId"));
        request.getSession().setAttribute("selectedTournament", selectedTournament);
        return pagesResourceBundle.getString("addEvent");
    }
}
