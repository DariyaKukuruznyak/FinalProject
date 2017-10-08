package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class ShowEventsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User authorizedUser = (User) currentSession.getAttribute("user");
        Collection<Event> events;
        EventService eventService = ServiceFactory.getInstance().getEventService();
        if (authorizedUser != null && authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
            events = eventService.getEventsByBookmakerId(authorizedUser.getId());
        } else {
            events = eventService.getAll();
        }
        currentSession.setAttribute("events", events);
        return pagesResourceBundle.getString("events");
    }
}
