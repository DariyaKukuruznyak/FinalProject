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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        Set<Outcome> collectedOutcomes = (Set<Outcome>) currentSession.getAttribute(COLLECTED_OUTCOMES);
        if (collectedOutcomes == null) {
            collectedOutcomes = new HashSet<>();
        }
        OutcomeService outcomeService = serviceFactory.getOutcomeService();
        Outcome movedOutcome = outcomeService.getById(request.getParameter(OUTCOME_ID));
        String action = request.getParameter(ACTION);
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
        currentSession.setAttribute(COLLECTED_OUTCOMES, collectedOutcomes);
        currentSession.setAttribute(TOTAL_COEFFICIENT, totalCoefficient);
        return pagesResourceBundle.getString("home");
    }
}
