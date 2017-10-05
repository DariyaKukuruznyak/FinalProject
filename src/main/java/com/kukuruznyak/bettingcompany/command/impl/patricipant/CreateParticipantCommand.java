package com.kukuruznyak.bettingcompany.command.impl.patricipant;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.ParticipantBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateParticipantCommand extends Command {
    private ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        try {
            Participant participant = fillParticipant(request);
            if (participantService.isValidParticipant(participant)) {
                participantService.add(participant);
                request.getSession().setAttribute("successMessage", "Participant was created successfully");
                LOGGER.info("Participant was created successfully");
//                request.getSession().setAttribute("participants", participantService.getTournamentsByParticipant());
                request.getSession().setAttribute("participant", participant);
            } else {
                throw new ApplicationException("Invalid participant");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute("errorMessage", e.getMessage());
        }
        return pagesResourceBundle.getString("addParticipant");
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
