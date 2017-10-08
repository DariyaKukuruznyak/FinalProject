package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class HomeCommand extends Command {
    private EventService eventService = ServiceFactory.getInstance().getEventService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Collection<Event> activeEvents = eventService.getActiveEvens(EventStatus.INPROGRESS);
        request.getSession().setAttribute("activeEvents",activeEvents);
        request.getSession().setAttribute("selectedEvents",activeEvents);
        return pagesResourceBundle.getString("home");
    }
}