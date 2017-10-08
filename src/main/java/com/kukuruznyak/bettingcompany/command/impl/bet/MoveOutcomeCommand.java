package com.kukuruznyak.bettingcompany.command.impl.bet;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.service.OutcomeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class MoveOutcomeCommand extends Command {
    private static final String ADD_ACTION = "add";
    private static final String REMOVE_ACTION = "remove";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        Set<Outcome> collectedOutcomes = (Set<Outcome>) currentSession.getAttribute("collectedOutcomes");
        if (collectedOutcomes == null) {
            collectedOutcomes = new HashSet<>();
        }
        OutcomeService outcomeService = serviceFactory.getOutcomeService();
        Outcome movedOutcome = outcomeService.getById(request.getParameter("outcomeId"));
        String action = request.getParameter("action");
        if (movedOutcome != null) {
            if (action.equals(ADD_ACTION)) {
                collectedOutcomes.add(movedOutcome);
            } else {
                if (collectedOutcomes.size() > 0 && action.equals(REMOVE_ACTION)) {
                    collectedOutcomes.remove(movedOutcome);
                }
            }
        }
        double totalCoefficient = 1;
        for (Outcome outcome : collectedOutcomes) {
            totalCoefficient *= outcome.getCoefficient();
        }
        currentSession.setAttribute("collectedOutcomes", collectedOutcomes);
        currentSession.setAttribute("totalCoefficient", totalCoefficient);
        return pagesResourceBundle.getString("home");
    }
}
