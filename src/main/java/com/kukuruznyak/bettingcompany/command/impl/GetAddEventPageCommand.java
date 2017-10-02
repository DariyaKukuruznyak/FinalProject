package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.TournamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAddEventPageCommand extends Command {
    private TournamentService tournamentService = TournamentService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        User authorizedUser = (User) request.getSession().getAttribute("user");
        if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
            LOGGER.error("Access denied");
            throw new ApplicationException("Access denied");
        }
        List<Tournament> activeTournaments = tournamentService.getActiveTournament();
        request.getSession().setAttribute("activeTournaments", activeTournaments);
        return pagesResourceBundle.getString("addEvent");
    }
}
