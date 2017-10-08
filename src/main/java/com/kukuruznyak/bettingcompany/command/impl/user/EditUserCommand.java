package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            UserService userService = serviceFactory.getUserService();
            User editedUser = userService.getUserById(request.getParameter("id"));
            editedUser = editUser(request, editedUser);
            if (userService.isValidUser(editedUser)) {
                userService.update(editedUser);
                currentSession.setAttribute("successMessage", "User was updated successfully.");
                currentSession.setAttribute("editedUser", editedUser);
                LOGGER.info("User with id = " + editedUser.getId() + " updated.");
                User authorizedUser = (User) currentSession.getAttribute("user");
                if (editedUser.getId().equals(authorizedUser.getId())) {
                    currentSession.setAttribute("user", editedUser);
                }
            } else {
                throw new ApplicationException("Incorrect user!");
            }
        } catch (ApplicationException e) {
            currentSession.setAttribute("errorMessage", e.getMessage());
            LOGGER.error(e.getMessage());
        } finally {
            return pagesResourceBundle.getString("editUser");
        }
    }

    private User editUser(HttpServletRequest request, User user) {
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        return user;
    }
}