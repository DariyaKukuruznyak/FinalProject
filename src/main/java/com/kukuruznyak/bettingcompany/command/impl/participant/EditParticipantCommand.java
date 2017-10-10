package com.kukuruznyak.bettingcompany.command.impl.participant;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.RequestAttributeConstants;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditParticipantCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            ParticipantService participantService = serviceFactory.getParticipantService();
            Participant participant = participantService.getParticipantById(request.getParameter(PARTICIPANT_ID));
            participant = editParticipant(request, participant);
            if (participantService.isValidParticipant(participant)) {
                participantService.update(participant);
                LOGGER.info("Participant with id = " + participant.getId() + " updated.");
                currentSession.setAttribute(SUCCESS_MESSAGE, "Participant was updated successfully.");
                currentSession.setAttribute(RequestAttributeConstants.PARTICIPANT, participant);
                TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
                currentSession.setAttribute(ACTIVE_TOURNAMENTS, tournamentService.getActiveTournament());
            } else {
                throw new ApplicationException("Incorrect participant!");
            }
            return pagesResourceBundle.getString("editParticipant");
        } catch (ApplicationException e) {
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
            LOGGER.error(e.getMessage());
            return pagesResourceBundle.getString("editParticipant");
        }
    }

    private Participant editParticipant(HttpServletRequest request, Participant participant) {
        participant.setName(request.getParameter(NAME));
        participant.setAge(new Integer(request.getParameter(AGE)));
        participant.setWeight(new Integer(request.getParameter(WEIGHT)));
        participant.setTrainer(request.getParameter(TRAINER));
        participant.setJockey(request.getParameter(JOCKEY));
        return participant;
    }
}