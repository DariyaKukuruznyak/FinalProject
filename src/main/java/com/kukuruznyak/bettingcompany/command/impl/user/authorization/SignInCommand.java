package com.kukuruznyak.bettingcompany.command.impl.user.authorization;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        UserService userService = serviceFactory.getUserService();
        HttpSession currentSession = request.getSession();
        if (userService.isUserExist(login, request.getParameter("password"))) {
            User user = userService.getUserByLogin(request.getParameter("login"));
            currentSession.setAttribute("user", user);
            LOGGER.info("User " + user.getLogin() + " signed in");
            currentSession.setAttribute("adminRole", UserRole.ADMINISTRATOR);
            currentSession.setAttribute("bookmakerRole", UserRole.BOOKMAKER);
            currentSession.setAttribute("riskControllerRole", UserRole.RISK_CONTROLLER);
            currentSession.setAttribute("clientRole", UserRole.CLIENT);
            return pagesResourceBundle.getString("home");
        } else {
            currentSession.setAttribute("errorMessage", "Incorrect input! Try again.");
            return pagesResourceBundle.getString("login");
        }
    }
}
