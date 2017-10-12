package com.kukuruznyak.bettingcompany.command.impl.bet;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import com.kukuruznyak.bettingcompany.service.BetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBetDescriptionCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String description = request.getParameter(DESCRIPTION);
        BetService betService = serviceFactory.getBetService();
        Bet bet = betService.getById(new Long(request.getParameter(ID)));
        bet.setDescription(description);
        betService.update(bet);
        return pagesResourceBundle.getString(BETS_PAGE);
    }
}

