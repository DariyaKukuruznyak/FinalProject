package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
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
        EventService eventService = ServiceFactory.getInstance().getEventService();
        Collection<Event> activeEvents = eventService.getActiveEvens(EventStatus.INPROGRESS);
        currentSession.setAttribute("selectedOutcomes", activeEvents.iterator().next().getMarkets().iterator().next().getOutcomes());
        currentSession.setAttribute("activeEvents", activeEvents);
        currentSession.setAttribute("selectedEvents", activeEvents);
        return pagesResourceBundle.getString("home");
    }
}