package com.kukuruznyak.bettingcompany.command.impl.participant;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.ParticipantBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateParticipantCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        Participant participant = fillParticipant(request);
        ParticipantService participantService = serviceFactory.getParticipantService();
        if (participantService.isValidParticipant(participant)) {
            participant = participantService.add(participant);
            HttpSession currentSession = request.getSession();
            currentSession.setAttribute(PARTICIPANT_ID, participant.getId());
            currentSession.setAttribute(SUCCESS_MESSAGE, PARTICIPANT_CREATED_SUCCESSFULLY);
            LOGGER.info(PARTICIPANT_CREATED_SUCCESSFULLY);
            TournamentService tournamentService = serviceFactory.getTournamentService();
            currentSession.setAttribute(TOURNAMENTS, tournamentService.getActiveTournament());
            currentSession.setAttribute(PARTICIPANT, participant);
        } else {
            throw new ApplicationException(INCORRECT_PARTICIPANT);
        }
        return pagesResourceBundle.getString(EDIT_PARTICIPANT_PAGE);
    }

    private Participant fillParticipant(HttpServletRequest request) {
        return new ParticipantBuilder()
                .buildName(request.getParameter(NAME))
                .buildAge(Integer.valueOf(request.getParameter(AGE)))
                .buildWeight(Integer.valueOf(request.getParameter(WEIGHT)))
                .buildTrainer(request.getParameter(TRAINER))
                .buildJockey(request.getParameter(JOCKEY))
                .build();
    }
}
