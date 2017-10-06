package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class ShowEventsCommand extends Command {
    private EventService eventService = ServiceFactory.getInstance().getEventService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        User authorizedUser = (User) request.getSession().getAttribute("user");
        Collection<Event> events;
        if (authorizedUser != null && authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
            events = eventService.getEventsByBookmakerId(authorizedUser.getId());
        } else {
            events = eventService.getAll();
        }
        request.getSession().setAttribute("events", events);
        return pagesResourceBundle.getString("events");
    }
}
