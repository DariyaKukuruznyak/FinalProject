package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowParticipantsCommand extends Command {
    private ParticipantService participantService = ParticipantService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        List<Participant> participants = participantService.getParticipants();
        request.getSession().setAttribute("participants", participants);
        return pagesResourceBundle.getString("participants");
    }
}
