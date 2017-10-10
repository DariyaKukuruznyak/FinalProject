package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddTournamentPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(TOURNAMENT);
        return pagesResourceBundle.getString(ADD_TOURNAMENT_PAGE);
    }
}
