package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class GetAddEventPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User authorizedUser = (User) currentSession.getAttribute(USER);
        if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
            LOGGER.error("Access denied");
            throw new ApplicationException("Access denied");
        }
        TournamentService tournamentService = serviceFactory.getTournamentService();
        Collection<Tournament> activeTournaments = tournamentService.getActiveTournament();
        currentSession.setAttribute(TOURNAMENTS, activeTournaments);
        return pagesResourceBundle.getString(ADD_EVENT_PAGE);
    }
}
