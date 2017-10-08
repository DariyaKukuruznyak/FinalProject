package com.kukuruznyak.bettingcompany.command.impl.get;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetEditEventPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EventService eventService = serviceFactory.getEventService();
        Event event = eventService.getById(request.getParameter("eventId"));
        HttpSession currentSession = request.getSession();
        currentSession.setAttribute("lockedStatus", EventStatus.LOCKED);
        currentSession.setAttribute("inprogressStatus", EventStatus.INPROGRESS);
        currentSession.setAttribute("event", event);
        return pagesResourceBundle.getString("editEvent");
    }
}
