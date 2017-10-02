package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.participantbuilder.ParticipantBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateParticipantCommand extends Command {
    private ParticipantService participantService = ParticipantService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        try {
            Participant participant = fillParticipant(request);
            if (participantService.isValidParticipant(participant)) {
                participantService.add(participant);
                request.getSession().setAttribute("successMessage", "Participant was created successfully");
                LOGGER.error("Participant was created successfully");
            } else {
                throw new ApplicationException("Invalid participant");
            }
        } catch (ApplicationException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute("errorMessage", e.getMessage());
        }
        request.getSession().setAttribute("participants",participantService.getParticipants());
        return pagesResourceBundle.getString("participants");
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
