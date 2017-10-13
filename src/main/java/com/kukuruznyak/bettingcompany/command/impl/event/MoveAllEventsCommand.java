package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.impl.DefaultCommand;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command for selecting or clearing select of events on home page
 */
public class MoveAllEventsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter(ACTION);
        HttpSession currentSession = request.getSession();
        if (action.equals(EXCLUDE_ACTION)) {
            currentSession.setAttribute(SELECTED_EVENTS, null);
        } else {
            EventService eventService = serviceFactory.getEventService();
            currentSession.setAttribute(SELECTED_EVENTS, eventService.getEvensByStatus(EventStatus.INPROGRESS));
        }
       return new DefaultCommand().execute(request, response);

    }
}