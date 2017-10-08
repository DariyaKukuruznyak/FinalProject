package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.event.MarketNames;
import com.kukuruznyak.bettingcompany.entity.event.eventbuilder.EventBuilder;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User authorizedUser = (User) currentSession.getAttribute("user");
        try {
            if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
                throw new ApplicationException("Access denied");
            }
            Tournament tournament = (Tournament) currentSession.getAttribute("selectedTournament");
            if (tournament == null) {
                throw new ApplicationException("No tournament selected.");
            }
            Event event = new EventBuilder()
                    .buildBookmaker(authorizedUser)
                    .buildTournament(tournament)
                    .build();
            EventService eventService = serviceFactory.getEventService();
            event = eventService.add(event);
            eventService.createMarket(event, MarketNames.WINNER);
            currentSession.setAttribute("event", event);
            currentSession.setAttribute("successMessage", "Event was created successfully");
            currentSession.setAttribute("lockedStatus", EventStatus.LOCKED);
            currentSession.setAttribute("inprogressStatus", EventStatus.INPROGRESS);
            currentSession.removeAttribute("selectedTournament");
            LOGGER.info("Event was created successfully");
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            currentSession.setAttribute("errorMessage", e.getMessage());
        }
        return pagesResourceBundle.getString("editEvent");
    }
}
