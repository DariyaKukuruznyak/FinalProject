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
                currentSession.setAttribute(SUM, request.getParameter(SUM));
                return new GetLoginPageCommand().execute(request, response);
            }
            if (!authorizedUser.getUserRole().equals(UserRole.CLIENT)) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.ACCESS_DENIED));
            }
            Set<Outcome> collectedOutcomes = (Set<Outcome>) currentSession.getAttribute(COLLECTED_OUTCOMES);
            if (collectedOutcomes == null) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.EMPTY_BASKET));
            }
            BigDecimal sum;
            try {
                sum = new BigDecimal(request.getParameter(SUM));
            } catch (Exception e) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_INPUT));
            }
            Bet bet = new BetBuilder(authorizedUser.getId(), sum).build();
            BetService betService = serviceFactory.getBetService();
            bet = betService.writeOutcomesIntoBet(bet, collectedOutcomes);
            if (betService.placeBet(bet)) {
                currentSession.removeAttribute(COLLECTED_OUTCOMES);
                currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.BET_PLACED));
                request.setAttribute(USER, serviceFactory.getClientService().getClientById(authorizedUser.getId()));
                return pagesResourceBundle.getString(HOME_PAGE);
            } else {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.BET_NOT_PLACED));
            }
        } catch (ApplicationException e) {
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
            return pagesResourceBundle.getString(HOME_PAGE);
        }
    }
}

