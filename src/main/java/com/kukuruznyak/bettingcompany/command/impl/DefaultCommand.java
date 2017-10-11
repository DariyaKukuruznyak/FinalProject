package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class DefaultCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        EventService eventService = serviceFactory.getEventService();
        Collection<Event> activeEvents = eventService.getEvensByStatus(EventStatus.INPROGRESS);
        currentSession.setAttribute(ACTIVE_EVENTS, activeEvents);
        currentSession.setAttribute(SELECTED_EVENTS, activeEvents);
        return pagesResourceBundle.getString(HOME_PAGE);
    }
}