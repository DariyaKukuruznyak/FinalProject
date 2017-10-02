package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.BetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowBetsCommand extends Command {
    private BetService betService = BetService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        User currentUser = (User) request.getSession().getAttribute("user");
        try {
            if (currentUser == null) {
                throw new ApplicationException("Unexpected request!");
            }
            switch (currentUser.getUserRole()) {
                case CLIENT:
                    return showBetsOfClient(request, currentUser.getId());
                default: {
                    throw new ApplicationException("Unexpected request!");
                }
            }
        } catch (ApplicationException e) {
            LOGGER.error(e);
            request.getSession().setAttribute("errorMessage", e);
            return pagesResourceBundle.getString("home");
        }
    }

    private String showBetsOfClient(HttpServletRequest request, Long clientId) {
        List<Bet> bets = betService.getBetByUser(clientId);
        request.getSession().setAttribute("bets", bets);
        return pagesResourceBundle.getString("bets");
    }
}