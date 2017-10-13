package com.kukuruznyak.bettingcompany.command.impl.participant;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.service.ParticipantService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command removes a selected participant
 */
public class DeleteParticipantCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ParticipantService participantService = serviceFactory.getParticipantService();
        participantService.delete(new Long(request.getParameter(PARTICIPANT_ID)));
        return new ShowParticipantsCommand().execute(request, response);
    }
}
