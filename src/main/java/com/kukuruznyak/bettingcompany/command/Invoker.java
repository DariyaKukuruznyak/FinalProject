package com.kukuruznyak.bettingcompany.command;

import com.kukuruznyak.bettingcompany.command.impl.HomeCommand;
import com.kukuruznyak.bettingcompany.command.impl.bet.ShowBetsCommand;
import com.kukuruznyak.bettingcompany.command.impl.event.CreateEventCommand;
import com.kukuruznyak.bettingcompany.command.impl.get.*;
import com.kukuruznyak.bettingcompany.command.impl.patricipant.CreateParticipantCommand;
import com.kukuruznyak.bettingcompany.command.impl.patricipant.DeleteParticipantCommand;
import com.kukuruznyak.bettingcompany.command.impl.patricipant.EditParticipantCommand;
import com.kukuruznyak.bettingcompany.command.impl.patricipant.ShowParticipantsCommand;
import com.kukuruznyak.bettingcompany.command.impl.tournament.*;
import com.kukuruznyak.bettingcompany.command.impl.user.CreateUserCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.DeleteUserCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.EditUserCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.authorization.LogoutCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.authorization.RegisterCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.authorization.SignInCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Invoker {
    private static Invoker instance;

    private Invoker() {
    }

    public static Invoker getInstance() {
        if (instance == null) {
            synchronized (Invoker.class) {
                if (instance == null) {
                    instance = new Invoker();
                }
            }
        }
        return instance;
    }

    private Command routeCommand(String url) {
        switch (url) {
            case "home":
                return new HomeCommand();
            case "login":
                return new GetLoginPageCommand();
            case "signIn":
                return new SignInCommand();
            case "register":
                return new GetRegisterPageCommand();
            case "join":
                return new RegisterCommand();
            case "logout":
                return new LogoutCommand();
           case "betsHistory":
                return new ShowBetsCommand();
            case "profile":
                return new GetProfilePageCommand();
            case "editUser":
                return new EditUserCommand();
            case "users":
                return new GetStaffPageCommand();
            case "deleteUser":
                return new DeleteUserCommand();
            case "addUser":
                return new GetAddUserPageCommand();
            case "createUser":
                return new CreateUserCommand();
            case "addEvent":
                return new GetAddEventPageCommand();
            case "createEvent":
                return new CreateEventCommand();
            case "participants":
                return new ShowParticipantsCommand();
            case "addParticipant":
                return new GetAddParticipantPageCommand();
            case "createParticipant":
                return new CreateParticipantCommand();
            case "editParticipant":
                return new GetEditParticipantPageCommand();
            case "updateParticipant":
                return new EditParticipantCommand();
            case "deleteParticipant":
                return new DeleteParticipantCommand();
            case "tournaments":
                return new ShowTournamentsCommand();
            case "addTournament":
                return new GetAddTournamentPageCommand();
            case "createTournament":
                return new CreateTournamentCommand();
            case "editTournament":
                return new GetEditTournamentPageCommand();
            case "updateTournament":
                return new EditTournamentCommand();
            case "deleteTournament":
                return new DeleteTournamentCommand();
            case "excludeParticipant":
                return new ExcludeParticipantFromTournamentCommand();
            case "includeParticipant":
                return new IncludeParticipantToTournamentCommand();

            default:
                return new HomeCommand();
        }
    }

    public String invoke(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        if (command == null) {
            command = "home";
        }
        return routeCommand(command).execute(request, response);
    }
}
