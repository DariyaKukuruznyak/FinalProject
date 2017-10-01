package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class RegisterCommand extends Command {
    private UserService userService = UserService.getInstance();
    private static ResourceBundle validationBundle = ResourceBundle.getBundle("validationPatterns");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        if (userService.getUserByLogin(request.getParameter("login")) != null) {
            request.getSession().setAttribute("errorMessage", "User with login '" + request.getParameter("login") + "' already exist!");
            LOGGER.error(request.getAttribute("errorMessage"));
            return pagesResourceBundle.getString("register");
        }
        if (!isValidRegistration(request)) {
            LOGGER.error(request.getAttribute("errorMessage"));
            return pagesResourceBundle.getString("register");
        }
        User user = buildUser(request);
        user = userService.add(user);
        request.getSession().setAttribute("user", user);
        LOGGER.info("New user " + user.getLogin() + " joined");
        return pagesResourceBundle.getString("home");
    }

    private boolean isValidRegistration(HttpServletRequest request) {
        if (!request.getParameter("firstName").matches(validationBundle.getString("firstName"))) {
            request.getSession().setAttribute("errorMessage", "Incorrect first name!");
            return false;
        }
        if (!request.getParameter("lastName").matches(validationBundle.getString("lastName"))) {
            request.getSession().setAttribute("errorMessage", "Incorrect last name!");
            return false;
        }
        if (!request.getParameter("login").matches(validationBundle.getString("login"))) {
            request.getSession().setAttribute("errorMessage", "Incorrect login!");
            return false;
        }
/*
        if (!request.getParameter("email").matches(validationBundle.getString("email"))) {
            request.setAttribute("errorMessage", "Incorrect email!");
            return false;
        }
*/
        if (!request.getParameter("password").matches(validationBundle.getString("password"))) {
            request.getSession().setAttribute("errorMessage", "Incorrect password!");
            return false;
        }
        if (!request.getParameter("securityNumber").matches(validationBundle.getString("securityNumber"))) {
            request.getSession().setAttribute("errorMessage", "Incorrect security number!");
            return false;
        }
        if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
            request.getSession().setAttribute("errorMessage", "Passwords are not equal!");
            return false;
        }
        if (!request.getParameter("securityNumber").equals(request.getParameter("confirmSecurityNumber"))) {
            request.getSession().setAttribute("errorMessage", "Security numbers are not equal!");
            return false;
        }
        return true;
    }

    private User buildUser(HttpServletRequest request) {
        return new UserBuilder()
                .buildFirstName(request.getParameter("firstName"))
                .buildLastName(request.getParameter("lastName"))
                .buildLogin(request.getParameter("login"))
                .buildEmail(request.getParameter("email"))
                .buildPassword(request.getParameter("password"))
                .buildSecurityNumber(request.getParameter("securityNumber"))
                .buildUserRole(UserRole.CLIENT)
                .build();
    }
}