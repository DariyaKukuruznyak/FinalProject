package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeEventStatusCommand extends Command {
    private static final String INPROGRESS_STATUS = "inprogress";
    private static final String FINISHED_STATUS = "finished";
    private static final String SUSPEND_STATUS = "suspend";
    private static final String ACTIVATE_STATUS = "activate";
    private static final String LOCKED_STATUS = "locked";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User authorizedUser = (User) request.getSession().getAttribute("user");
        Event event = (Event) request.getSession().getAttribute("event");
        String status = request.getParameter("status");
        try {
            if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
                throw new ApplicationException("Access denied");
            }
            EventService eventService = ServiceFactory.getInstance().getEventService();
            switch (status) {
                case LOCKED_STATUS:
                    event.setStatus(EventStatus.LOCKED);
                    break;
                case INPROGRESS_STATUS:
                    event.setStatus(EventStatus.INPROGRESS);
                    break;
                case FINISHED_STATUS:
                    if (event.getTournament().getWinner() == null) {
                        throw new ApplicationException("Result of tournament is unknown!");
                    }
                    event.setStatus(EventStatus.FINISHED);
                    eventService.finishEvent(event);
                    break;
                case SUSPEND_STATUS:
                    event.setSuspended(true);
                    break;
                case ACTIVATE_STATUS:
                    event.setSuspended(false);
                    break;
                default:
                    throw new ApplicationException("Unexpected request!");
            }
            eventService.update(event);
            request.getSession().setAttribute("event", event);
            request.getSession().setAttribute("successMessage", "Event has been " + status);
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute("errorMessage", e.getMessage());
        }
        return pagesResourceBundle.getString("editEvent");
    }


}
