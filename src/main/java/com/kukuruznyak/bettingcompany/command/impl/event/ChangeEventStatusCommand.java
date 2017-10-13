package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.EventStatus;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command changes statuses of event
 */
public class ChangeEventStatusCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User authorizedUser = (User) currentSession.getAttribute(USER);
        Event event = (Event) currentSession.getAttribute(EVENT);
        String status = request.getParameter(STATUS);
        try {
            if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.ACCESS_DENIED));
            }
            EventService eventService = serviceFactory.getEventService();
            switch (status) {
                case LOCKED_STATUS:
                    event.setStatus(EventStatus.LOCKED);
                    break;
                case INPROGRESS_STATUS:
                    event.setStatus(EventStatus.INPROGRESS);
                    break;
                case FINISHED_STATUS:
                    if (event.getTournament().getWinner() == null) {
                        throw new ApplicationException(StringMessages.getMessage(StringMessages.TOURNAMENT_RESULT_UNKNOWN));
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
                    throw new ApplicationException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
            }
            eventService.update(event);
            currentSession.setAttribute(EVENT, event);
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
        }
        return pagesResourceBundle.getString(EDIT_EVENT_PAGE);
    }


}
