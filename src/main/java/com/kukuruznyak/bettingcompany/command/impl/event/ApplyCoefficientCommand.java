package com.kukuruznyak.bettingcompany.command.impl.event;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.OutcomeService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplyCoefficientCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            User authorizedUser = (User) currentSession.getAttribute("user");
            if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
                throw new ApplicationException("Access denied");
            }
            double coefficient = new Double(request.getParameter("coefficient"));
            if (coefficient < 1) {
                throw new ApplicationException("Forbidden coefficient");
            }
            OutcomeService outcomeService = serviceFactory.getOutcomeService();
            Outcome outcome = outcomeService.getById(request.getParameter("outcomeId"));
            outcome.setCoefficient(coefficient);
            outcomeService.update(outcome);
            Event event = (Event) currentSession.getAttribute("event");
            EventService eventService = serviceFactory.getEventService();
            event = eventService.getById(event.getId());
            currentSession.setAttribute("event", event);
            currentSession.setAttribute("successMessage", "Coefficient for outcome " + outcome.getName() + " has been changed successfully.");
        } catch (ApplicationException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
            currentSession.setAttribute("errorMessage", e.getMessage());
        }
        return pagesResourceBundle.getString("editEvent");
    }
}
