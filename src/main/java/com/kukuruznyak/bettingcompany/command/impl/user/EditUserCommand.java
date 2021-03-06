package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ClientService;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            UserService userService = serviceFactory.getUserService();
            User editedUser = userService.getUserById(request.getParameter(ID));
            editedUser = editUser(request, editedUser);
            if (!userService.isValidUser(editedUser)) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_USER));
            }
            userService.update(editedUser);
            currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.USER_UPDATED_SUCCESSFULLY));
            currentSession.setAttribute(EDITED_USER, editedUser);
            User authorizedUser = (User) currentSession.getAttribute(USER);
            if (editedUser.getId().equals(authorizedUser.getId())) {
                currentSession.setAttribute(USER, editedUser);
            } else {
                if (editedUser.getUserRole().equals(UserRole.CLIENT)) {
                    ClientService clientService = ServiceFactory.getInstance().getClientService();
                    currentSession.setAttribute(EDITED_USER, clientService.getClientById(editedUser.getId()));
                } else {
                    currentSession.setAttribute(EDITED_USER, editedUser);
                }
            }
            return pagesResourceBundle.getString(EDIT_USER_PAGE);
        } catch (ApplicationException e) {
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
            LOGGER.error(e.getMessage());
            return pagesResourceBundle.getString(EDIT_USER_PAGE);
        }
    }

    private User editUser(HttpServletRequest request, User user) {
        user.setFirstName(request.getParameter(FIRST_NAME));
        user.setLastName(request.getParameter(LAST_NAME));
        user.setEmail(request.getParameter(EMAIL));
        return user;
    }
}