package com.kukuruznyak.bettingcompany.command.impl.patricipant;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteParticipantCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ParticipantService participantService = serviceFactory.getParticipantService();
        participantService.delete(request.getParameter("participantId"));
        return new ShowParticipantsCommand().execute(request, response);
    }
}
