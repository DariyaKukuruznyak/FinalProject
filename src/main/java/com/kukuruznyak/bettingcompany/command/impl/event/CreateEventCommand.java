package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
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
            Event event = fillEvent(request, authorizedUser);
            eventService.add(event);
            request.getSession().setAttribute("successMessage", "Event was created successfully");
            LOGGER.error("Event was created successfully");
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute("errorMessage", e.getMessage());
        }
        return pagesResourceBundle.getString("addEvent");
    }

    private Event fillEvent(HttpServletRequest request, User bookmaker) {
        int maxWin;
        try {
            maxWin = Integer.valueOf(request.getParameter("maxWin"));
        } catch (NumberFormatException e) {
            throw new ApplicationException("Invalid max win");
        }
        return new EventBuilder()
                .buildBookmaker(bookmaker)
                .buildMaxWin(maxWin)
                .buildTournament((Tournament) request.getAttribute("tournament"))
                .build();
    }
}
