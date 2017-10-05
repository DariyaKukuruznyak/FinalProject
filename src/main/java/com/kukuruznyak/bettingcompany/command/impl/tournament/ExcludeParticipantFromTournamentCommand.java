package com.kukuruznyak.bettingcompany.command.impl.tournament;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.impl.get.GetEditTournamentPageCommand;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcludeParticipantFromTournamentCommand extends Command {
    private TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
    private ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Tournament tournament = (Tournament) request.getSession().getAttribute("tournament");
        tournamentService.excludeParticipant(request.getParameter("participantId"), tournament.getId().toString());
        request.getSession().setAttribute("tournament", tournamentService.getById(tournament.getId().toString()));
        request.getSession().setAttribute("participants", participantService.getParticipants());
        return new GetEditTournamentPageCommand().execute(request, response);
    }
}
