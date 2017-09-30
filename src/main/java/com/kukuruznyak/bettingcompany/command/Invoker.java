package com.kukuruznyak.bettingcompany.command;

import com.kukuruznyak.bettingcompany.command.impl.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Invoker {
    private static Invoker instance;
    private static final Map<String, Command> urlPatternsList = new HashMap<>();
    private static final ResourceBundle URL_PATTERNS = ResourceBundle.getBundle("urlPatterns");
    private Invoker() {
        urlPatternsList.put(URL_PATTERNS.getString("home"), new HomeCommand());
        urlPatternsList.put(URL_PATTERNS.getString("error"), new ErrorCommand());
        urlPatternsList.put(URL_PATTERNS.getString("login"), new SignInCommand());
        urlPatternsList.put(URL_PATTERNS.getString("register"), new RegisterCommand());
        urlPatternsList.put(URL_PATTERNS.getString("addUser"), new AddUserCommand());
        urlPatternsList.put(URL_PATTERNS.getString("editUser"), new EditUserCommand());
        urlPatternsList.put(URL_PATTERNS.getString("addEvent"), new AddEventCommand());
        urlPatternsList.put(URL_PATTERNS.getString("editEvent"), new EditEventCommand());
        urlPatternsList.put(URL_PATTERNS.getString("addParticipant"), new AddParticipantCommand());
        urlPatternsList.put(URL_PATTERNS.getString("editParticipant"), new EditParticipantCommand());
        urlPatternsList.put(URL_PATTERNS.getString("clientEdit"), new EditUserCommand());
        urlPatternsList.put(URL_PATTERNS.getString("bets"), new BetsCommand());
        urlPatternsList.put(URL_PATTERNS.getString("events"), new EventsCommand());
    }
    public static Invoker getInstance() {
        if (instance == null) {
            instance = new Invoker();
        }
        return instance;
    }

    public String invoke(HttpServletRequest request, HttpServletResponse response) {
        Command command;
        System.out.println(request.getRequestURI());
        command = urlPatternsList.get(request.getRequestURI());
        return command.execute(request, response);
    }
}
