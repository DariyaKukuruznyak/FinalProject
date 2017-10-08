package com.kukuruznyak.bettingcompany.command.impl.patricipant;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ParticipantService;
import com.kukuruznyak.bettingcompany.service.TournamentService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditParticipantCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        try {
            ParticipantService participantService = ServiceFactory.getInstance().getParticipantService();
            Participant participant = participantService.getParticipantById(request.getParameter("participantId"));
            participant = editParticipant(request, participant);
            if (participantService.isValidParticipant(participant)) {
                participantService.update(participant);
                LOGGER.info("Participant with id = " + participant.getId() + " updated.");
                request.setAttribute("successMessage", "Participant was updated successfully.");
                request.getSession().setAttribute("participant", participant);
                TournamentService tournamentService = ServiceFactory.getInstance().getTournamentService();
                request.getSession().setAttribute("activeTournaments", tournamentService.getActiveTournament());
            } else {
                throw new ApplicationException("Incorrect participant!");
            }
        } catch (ApplicationException e) {
            request.getSession().setAttribute("errorMessage", e.getMessage());
            LOGGER.error(e.getMessage());
        } finally {
            return pagesResourceBundle.getString("editParticipant");
        }
    }

    private Participant editParticipant(HttpServletRequest request, Participant participant) {
        participant.setName(request.getParameter("name"));
        participant.setAge(new Integer(request.getParameter("age")));
        participant.setWeight(new Integer(request.getParameter("weight")));
        participant.setTrainer(request.getParameter("trainer"));
        participant.setJockey(request.getParameter("jockey"));
        return participant;
    }
}