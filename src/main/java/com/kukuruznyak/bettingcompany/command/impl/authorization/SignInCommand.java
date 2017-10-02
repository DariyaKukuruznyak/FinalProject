package com.kukuruznyak.bettingcompany.command.impl.authorization;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand extends Command {
    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        if (userService.isUserExist(request.getParameter("login"), request.getParameter("password"))) {
            User user = userService.getUserByLogin(request.getParameter("login"));
            request.getSession().setAttribute("user", user);
            LOGGER.info("User " + user.getLogin() + " signed in");
            request.getSession().setAttribute("adminRole", UserRole.ADMINISTRATOR);
            request.getSession().setAttribute("bookmakerRole", UserRole.BOOKMAKER);
            request.getSession().setAttribute("riskControllerRole", UserRole.RISK_CONTROLLER);
            request.getSession().setAttribute("clientRole", UserRole.CLIENT);
            return pagesResourceBundle.getString("home");
        } else {
            request.getSession().setAttribute("errorMessage", "Incorrect input! Try again.");
            return pagesResourceBundle.getString("login");
        }
    }
}
