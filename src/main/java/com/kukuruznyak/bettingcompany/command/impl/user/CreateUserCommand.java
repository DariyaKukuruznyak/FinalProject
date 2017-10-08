package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            UserService userService = serviceFactory.getUserService();
            if (userService.getUserByLogin(request.getParameter("login")) != null) {
                throw new ApplicationException("User with login '" + request.getParameter("login") + "' already exist!");
            }
            User user = fillUser(request);
            if (!userService.isValidUser(user)) {
                throw new ApplicationException("Incorrect user!");
            }
            userService.add(user);
            currentSession.setAttribute("successMessage", "New user was added successfully");
            return pagesResourceBundle.getString("addUser");
        } catch (ApplicationException e) {
            currentSession.setAttribute("errorMessage", e.getMessage());
            LOGGER.error(e.getMessage());
            return pagesResourceBundle.getString("addUser");
        }
    }

    private User fillUser(HttpServletRequest request) {
        return new UserBuilder()
                .buildFirstName(request.getParameter("firstName"))
                .buildLastName(request.getParameter("lastName"))
                .buildLogin(request.getParameter("login"))
                .buildEmail(request.getParameter("email"))
                .buildPassword(request.getParameter("password"))
                .buildUserRole(UserRole.valueOf(request.getParameter("userRole")))
                .build();
    }
}
