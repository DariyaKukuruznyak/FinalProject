package com.kukuruznyak.bettingcompany.command.impl.patricipant;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.ParticipantBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateParticipantCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Participant participant = fillParticipant(request);
        ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();
        if (participantService.isValidParticipant(participant)) {
            participant = participantService.add(participant);
            request.getSession().setAttribute("participantId", participant.getId());
            request.getSession().setAttribute("successMessage", "Participant was created successfully");
            LOGGER.info("Participant was created successfully");
            TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
            request.getSession().setAttribute("tournaments", tournamentService.getActiveTournament());
            request.getSession().setAttribute("participant", participant);
        } else {
            throw new ApplicationException("Invalid participant");
        }
        return pagesResourceBundle.getString("editParticipant");
    }

    private Participant fillParticipant(HttpServletRequest request) {
        return new ParticipantBuilder()
                .buildName(request.getParameter("name"))
                .buildAge(Integer.valueOf(request.getParameter("age")))
                .buildWeight(Integer.valueOf(request.getParameter("weight")))
                .buildTrainer(request.getParameter("trainer"))
                .buildJockey(request.getParameter("jockey"))
                .build();
    }
}
