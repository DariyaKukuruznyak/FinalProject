package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class HomeCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        EventService eventService = serviceFactory.getEventService();
        Collection<Event> activeEvents = eventService.getEvensByStatus(EventStatus.INPROGRESS);
        currentSession.setAttribute(ACTIVE_EVENTS, activeEvents);

        User user= ServiceFactory.getInstance().getUserService().getUserById("9");
        currentSession.setAttribute(USER, user);
        currentSession.setAttribute(BOOKMAKER_ROLE, UserRole.BOOKMAKER);


        return pagesResourceBundle.getString(HOME_PAGE);
    }
}