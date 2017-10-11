package com.kukuruznyak.bettingcompany.command.impl.bet;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.impl.user.authorization.GetLoginPageCommand;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.bet.builder.BetBuilder;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.BetService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

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
            Client authorizedUser = (Client) currentSession.getAttribute(USER);
            if (authorizedUser == null) {
                return new GetLoginPageCommand().execute(request, response);
            }
            if (!authorizedUser.getUserRole().equals(UserRole.CLIENT)) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.ACCESS_DENIED));
            }
            Set<Outcome> collectedOutcomes = (Set<Outcome>) currentSession.getAttribute(COLLECTED_OUTCOMES);
            if (collectedOutcomes == null) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.EMPTY_BASKET));
            }
            BigDecimal sumIn = new BigDecimal(currentSession.getAttribute(SUM).toString());
            Bet bet = new BetBuilder(authorizedUser, sumIn).build();
            BetService betService = serviceFactory.getBetService();
            bet = betService.writeOutcomesIntoBet(bet, collectedOutcomes);
            betService.add(bet);
            currentSession.removeAttribute(COLLECTED_OUTCOMES);
            currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.BET_PLACED));
            return pagesResourceBundle.getString(HOME_PAGE);
        } catch (ApplicationException e) {
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
            return pagesResourceBundle.getString(HOME_PAGE);
        }
    }
}

