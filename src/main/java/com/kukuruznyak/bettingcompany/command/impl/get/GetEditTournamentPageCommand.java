package com.kukuruznyak.bettingcompany.command.impl.get;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetEditTournamentPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
        Tournament tournament = tournamentService.getById(request.getParameter("tournamentId"));
        request.getSession().setAttribute("tournament", tournament);
        ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();
        request.getSession().setAttribute("participants", participantService.getParticipants());
        return pagesResourceBundle.getString("editTournament");
    }
}
