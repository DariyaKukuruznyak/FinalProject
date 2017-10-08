package com.kukuruznyak.bettingcompany.command.impl.bet;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.BetService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class ShowBetsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User currentUser = (User) currentSession.getAttribute("user");
        try {
            if (currentUser == null) {
                throw new ApplicationException("Unexpected request!");
            }
            Collection<Bet> bets;
            BetService betService = ServiceFactory.getInstance().getBetService();
            switch (currentUser.getUserRole()) {
                case CLIENT:
                    bets = betService.getBetByUser(currentUser.getId());
                    break;
                case RISK_CONTROLLER:
                    bets = betService.getAll();
                    break;
                default: {
                    throw new ApplicationException("Unexpected request!");
                }
            }
            currentSession.setAttribute("bets", bets);
            return pagesResourceBundle.getString("bets");
        } catch (ApplicationException e) {
            LOGGER.error(e);
            currentSession.setAttribute("errorMessage", e);
            return pagesResourceBundle.getString("home");
        }
    }
}
