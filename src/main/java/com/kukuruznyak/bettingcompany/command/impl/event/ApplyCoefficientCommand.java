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

public class ApplyCoefficientCommand extends Command {
    private EventService eventService = ServiceFactory.getInstance().getEventService();
    private OutcomeService outcomeService = ServiceFactory.getInstance().getOutcomeService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User authorizedUser = (User) request.getSession().getAttribute("user");
            if (!authorizedUser.getUserRole().equals(UserRole.BOOKMAKER)) {
                throw new ApplicationException("Access denied");
            }
            double coefficient = new Double(request.getParameter("coefficient"));
            if(coefficient<1){
                throw new ApplicationException("Forbidden coefficient");
            }
            Outcome outcome = outcomeService.getById(request.getParameter("outcomeId"));
            outcome.setCoefficient(coefficient);
            outcomeService.update(outcome);
            Event event = (Event) request.getSession().getAttribute("event");
            event = eventService.getById(event.getId());
            request.getSession().setAttribute("event", event);
            request.getSession().setAttribute("successMessage", "Coefficient for outcome "+outcome.getName()+" has been changed successfully.");
        } catch (ApplicationException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute("errorMessage", e.getMessage());
        }
        return pagesResourceBundle.getString("editEvent");
    }
}
