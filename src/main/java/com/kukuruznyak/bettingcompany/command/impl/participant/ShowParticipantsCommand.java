package com.kukuruznyak.bettingcompany.command.impl.participant;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.RequestAttributeConstants;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.service.ParticipantService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class ShowParticipantsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ParticipantService participantService =serviceFactory.getParticipantService();
        Collection<Participant> participants = participantService.getParticipants();
        request.getSession().setAttribute(PARTICIPANTS, participants);
        return pagesResourceBundle.getString("participants");
    }
}
