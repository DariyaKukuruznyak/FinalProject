package com.kukuruznyak.bettingcompany.command;

import com.kukuruznyak.bettingcompany.command.impl.EditUserCommand;
import com.kukuruznyak.bettingcompany.command.impl.GetProfileCommand;
import com.kukuruznyak.bettingcompany.command.impl.HomeCommand;
import com.kukuruznyak.bettingcompany.command.impl.ShowBetsCommand;
import com.kukuruznyak.bettingcompany.command.impl.authorization.*;

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
                return new GetProfileCommand();
            case "editUser":
                return new EditUserCommand();
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
