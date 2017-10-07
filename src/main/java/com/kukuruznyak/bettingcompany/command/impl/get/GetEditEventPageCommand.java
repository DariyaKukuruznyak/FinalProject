package com.kukuruznyak.bettingcompany.command.impl.get;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetEditEventPageCommand extends Command {
    private EventService eventService = ServiceFactory.getInstance().getEventService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Event event = eventService.getById(request.getParameter("eventId"));
        request.getSession().setAttribute("lockedStatus", EventStatus.LOCKED);
        request.getSession().setAttribute("inprogressStatus", EventStatus.INPROGRESS);
        request.getSession().setAttribute("event", event);
        return pagesResourceBundle.getString("editEvent");
    }
}
