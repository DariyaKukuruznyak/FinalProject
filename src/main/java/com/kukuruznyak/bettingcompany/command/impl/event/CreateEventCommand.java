package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
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

public class CreateEventCommand extends Command {
    private EventService eventService = ServiceFactory.getInstance().getEventService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        User authorizedUser = (User) request.getSession().getAttribute("user");
        try {
            if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
                throw new ApplicationException("Access denied");
            }
            Tournament tournament = (Tournament) request.getSession().getAttribute("selectedTournament");
            if (tournament == null) {
                throw new ApplicationException("No tournament selected.");
            }
            Event event = new EventBuilder()
                    .buildBookmaker(authorizedUser)
                    .buildTournament(tournament)
                    .build();
            event = eventService.add(event);
            eventService.createMarket(event, MarketNames.WINNER);
            request.getSession().setAttribute("event", event);
            request.getSession().setAttribute("successMessage", "Event was created successfully");
            request.getSession().removeAttribute("selectedTournament");
            LOGGER.info("Event was created successfully");
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute("errorMessage", e.getMessage());
        }
        return pagesResourceBundle.getString("addEvent");
    }
}
