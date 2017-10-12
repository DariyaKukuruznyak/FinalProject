/*
package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class MoveEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter(ACTION);
        actionRouter(action, request);
        return pagesResourceBundle.getString(HOME_PAGE);
    }

    private void actionRouter(String action, HttpServletRequest request) {
        HttpSession currentSession = request.getSession();
        Collection<Event> selectedEvents = (Collection<Event>) currentSession.getAttribute(SELECTED_EVENTS);
        if (action.equals(EXCLUDE_ACTION)) {
            Event event = findEvent(selectedEvents, request.getParameter(EVENT_ID));
            if (event != null) {
                selectedEvents.remove(event);
            }
        } else {
            Event event = findEvent((Collection<Event>) currentSession.getAttribute(ACTIVE_EVENTS), request.getParameter(EVENT_ID));
            if (event != null) {
                selectedEvents.add(event);
            }
        }
        currentSession.setAttribute(SELECTED_EVENTS, selectedEvents);
    }

    private Event findEvent(Collection<Event> events, String id) {
        Event movedEvent = null;
        if (id != null) {
            Long eventId = new Long(id);
            for (Event event : events) {
                if (event.getId().equals(eventId)) {
                    movedEvent = event;
                }
            }
        }
        return movedEvent;
    }
}*/
package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class MoveEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter(ACTION);
        actionRouter(action, request);
        return pagesResourceBundle.getString(HOME_PAGE);
    }

    private void actionRouter(String action, HttpServletRequest request) {
        HttpSession currentSession = request.getSession();
        Collection<Event> selectedEvents = (Collection<Event>) currentSession.getAttribute(SELECTED_EVENTS);
        if (action.equals(EXCLUDE_ACTION)) {
            Event event = findEvent(selectedEvents, request.getParameter(EVENT_ID));
            if (event != null) {
                selectedEvents.remove(event);
            }
        } else {
            Event event = findEvent((Collection<Event>) currentSession.getAttribute(ACTIVE_EVENTS), request.getParameter(EVENT_ID));
            if (event != null) {
                selectedEvents.add(event);
            }
        }
        currentSession.setAttribute(SELECTED_EVENTS, selectedEvents);
    }

    private Event findEvent(Collection<Event> events, String id) {
        Event movedEvent = null;
        if (id != null) {
            Long eventId = new Long(id);
            for (Event event : events) {
                if (event.getId().equals(eventId)) {
                    movedEvent = event;
                }
            }
        }
        return movedEvent;
    }
}