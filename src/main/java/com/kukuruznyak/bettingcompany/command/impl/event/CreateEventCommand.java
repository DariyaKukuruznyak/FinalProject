package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.event.MarketNames;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.EventBuilder;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User authorizedUser = (User) currentSession.getAttribute(USER);
        try {
            if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.ACCESS_DENIED));
            }
            Tournament tournament = (Tournament) currentSession.getAttribute(SELECTED_TOURNAMENT);
            if (tournament == null) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.NO_TOURNAMENT_SELECTED));
            }
            Event event = new EventBuilder()
                    .buildBookmaker(authorizedUser)
                    .buildTournament(tournament)
                    .build();
            EventService eventService = serviceFactory.getEventService();
            event = eventService.add(event);
            event = eventService.createMarket(event, MarketNames.WINNER);
            currentSession.setAttribute(EVENT, event);
            currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.EVENT_CREATED_SUCCESSFULLY));
            currentSession.setAttribute(LOCKED_STATUS, EventStatus.LOCKED);
            currentSession.setAttribute(INPROGRESS_STATUS, EventStatus.INPROGRESS);
            currentSession.removeAttribute(SELECTED_TOURNAMENT);
            LOGGER.info(StringMessages.getMessage(StringMessages.EVENT_CREATED_SUCCESSFULLY));
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
        }
        return pagesResourceBundle.getString(EDIT_EVENT_PAGE);
    }
}
