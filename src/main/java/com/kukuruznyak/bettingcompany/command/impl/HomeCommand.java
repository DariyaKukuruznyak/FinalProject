package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.tournament.Country;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.EventService;
import com.kukuruznyak.bettingcompany.service.TournamentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeCommand extends Command {
    private EventService eventService = EventService.getInstance();
    private TournamentService tournamentService = TournamentService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        List<Country> countriesWithActiveTournaments = tournamentService.countriesWithActiveTournaments();
        request.getSession().setAttribute("countries", countriesWithActiveTournaments);
        if (countriesWithActiveTournaments.size() > 0) {
            List<Tournament> firstCountryTournaments = countriesWithActiveTournaments.get(0).getTournaments();
            if (firstCountryTournaments.size() > 0) {
//                Event currentEvent = eventService.getEventByTournamentId(firstCountryTournaments.get(0).getId());
                Event currentEvent = eventService.getEventByTournamentId(5l);
                request.getSession().setAttribute("event", currentEvent);
                request.getSession().setAttribute("typeOfBet", TypeOfBet.values());
            }
        }
        return pagesResourceBundle.getString("home");
    }
}