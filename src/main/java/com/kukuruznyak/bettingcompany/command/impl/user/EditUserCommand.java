package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ClientService;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserCommand extends Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private ClientService clientService = ServiceFactory.getInstance().getClientService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        try {
            User editedUser = userService.getUserById(request.getParameter("id"));
            editedUser = editUser(request, editedUser);
            if (userService.isValidUser(editedUser)) {
                userService.update(editedUser);
                request.setAttribute("successMessage", "User was updated successfully.");
                request.getSession().setAttribute("editedUser", editedUser);
                LOGGER.info("User with id = " + editedUser.getId() + " updated.");
                User authorizedUser = (User) request.getSession().getAttribute("user");
                if (editedUser.getId().equals(authorizedUser.getId())) {
                    request.getSession().setAttribute("user", editedUser);
                }
            } else {
                throw new ApplicationException("Incorrect user!");
            }
        } catch (ApplicationException e) {
            request.getSession().setAttribute("errorMessage", e.getMessage());
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