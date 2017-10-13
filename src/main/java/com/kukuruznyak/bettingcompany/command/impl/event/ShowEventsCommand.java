package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Command returns page of events list
 */
public class ShowEventsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User authorizedUser = (User) currentSession.getAttribute(USER);
        EventService eventService = serviceFactory.getEventService();
        if (authorizedUser != null && authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
            Collection<Event> events = eventService.getEventsByBookmakerId(authorizedUser.getId());
            currentSession.setAttribute(EVENTS, events);
        }
        return pagesResourceBundle.getString(EVENTS_PAGE);
    }
}
