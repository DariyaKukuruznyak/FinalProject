package com.kukuruznyak.bettingcompany.command.impl.bet;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.impl.get.GetLoginPageCommand;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.builder.BetBuilder;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.BetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Set;

public class SetBetCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            User authorizedUser = (User) currentSession.getAttribute("user");
            if (authorizedUser == null) {
                return new GetLoginPageCommand().execute(request, response);
            }
            if (!authorizedUser.getUserRole().equals(UserRole.CLIENT)) {
                throw new ApplicationException("Access denied");
            }
            Set<Outcome> collectedOutcomes = (Set<Outcome>) currentSession.getAttribute("collectedOutcomes");
            if (collectedOutcomes == null) {
                throw new ApplicationException("Basket is empty.");
            }
            BigDecimal sumIn = new BigDecimal(currentSession.getAttribute("sum").toString());
            Bet bet = new BetBuilder(authorizedUser, sumIn).build();
            BetService betService = serviceFactory.getBetService();
            bet = betService.writeOutcomesIntoBet(bet, collectedOutcomes);
            betService.add(bet);
            currentSession.removeAttribute("collectedOutcomes");
            currentSession.setAttribute("successMessage", "Bet has been placed!");
            return pagesResourceBundle.getString("home");
        } catch (ApplicationException e) {
            currentSession.setAttribute("errorMessage", e.getMessage());
            return pagesResourceBundle.getString("home");
        }
    }
}

