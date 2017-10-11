package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.OutcomeService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplyCoefficientCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            User authorizedUser = (User) currentSession.getAttribute(USER);
            if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.ACCESS_DENIED));
            }
            double coefficient = new Double(request.getParameter(COEFFICIENT));
            if (coefficient < 1) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.FORBIDDEN_COEFFICIENT));
            }
            OutcomeService outcomeService = serviceFactory.getOutcomeService();
            Outcome outcome = outcomeService.getById(request.getParameter(OUTCOME_ID));
            outcome.setCoefficient(coefficient);
            outcomeService.update(outcome);
            Event event = (Event) currentSession.getAttribute(EVENT);
            EventService eventService = serviceFactory.getEventService();
            event = eventService.getById(event.getId());
            currentSession.setAttribute(EVENT, event);
            currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.COEFFICIENT_CHANGED_SUCCESSFULLY)
                    + outcome.getName());
        } catch (ApplicationException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
        }
        return pagesResourceBundle.getString(EDIT_EVENT_PAGE);
    }
}
