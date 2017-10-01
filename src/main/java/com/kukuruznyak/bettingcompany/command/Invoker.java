package com.kukuruznyak.bettingcompany.command;

import com.kukuruznyak.bettingcompany.command.impl.HomeCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.*;

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

    private Command routeUrl(String url) {
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
       /*     case "/add/user":
                return new AddUserCommand();
            case "/edit/user":
                return new EditUserCommand();
            case "/add/event":
                return new AddEventCommand();
            case "/edit/event":
                return new EditEventCommand();
            case "/add/participant":
                return new AddParticipantCommand();
            case "/edit/participant":
                return new EditParticipantCommand();
            case "/edit/client":
                return new EditUserCommand();
            case "/bets":
                return new BetsCommand();
            case "/events":
                return new EventsCommand();
                */
            default:
                return new HomeCommand();
        }
    }

    public String invoke(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        if (command == null) {
            command = "home";
        }
        return routeUrl(command).execute(request, response);
    }
}
