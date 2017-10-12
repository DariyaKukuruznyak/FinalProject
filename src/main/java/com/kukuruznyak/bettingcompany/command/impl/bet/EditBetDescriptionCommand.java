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
        String betId = request.getParameter(ID);
        if (description != null && betId != null) {
            BetService betService = serviceFactory.getBetService();
            Bet bet = betService.getById(new Long(betId));
            bet.setDescription(description);
            betService.update(bet);
        }
        return new ShowBetsCommand().execute(request, response);
    }
}

