package com.kukuruznyak.bettingcompany.command.impl.bet;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.BetService;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Command returns a page of bets-list by given the user role
 */
public class ShowBetsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User authorizedUser = (User) currentSession.getAttribute(USER);
        try {
            if (authorizedUser == null) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
            }
            Collection<Bet> bets = new LinkedList<>();
            BetService betService = serviceFactory.getBetService();
            switch (authorizedUser.getUserRole()) {
                case CLIENT:
                    bets = betService.getBetsByClientId(authorizedUser.getId());
                    break;
                case BOOKMAKER:
                    EventService eventService = serviceFactory.getEventService();
                    Collection<Event> events = eventService.getEventsByBookmakerId(authorizedUser.getId());
                    for (Event event : events) {
                        bets.addAll(betService.getBetsByEvent(event));
                    }
                    break;
                case RISK_CONTROLLER:
                    bets = betService.getAll();
                    break;
                default: {
                    throw new ApplicationException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
                }
            }
            currentSession.setAttribute(BETS, bets);
            return pagesResourceBundle.getString(BETS_PAGE);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            currentSession.setAttribute(ERROR_MESSAGE, e);
            return pagesResourceBundle.getString(HOME_PAGE);
        }
    }
}
