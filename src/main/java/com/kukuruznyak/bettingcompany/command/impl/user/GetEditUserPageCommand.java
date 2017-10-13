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
/**
 * Command returns a page for editing a user
 */
public class GetEditUserPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String editedUserId = request.getParameter(ID);
        if (editedUserId == null) {
            throw new ApplicationException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
        }
        UserService userService = serviceFactory.getUserService();
        User editedUser = userService.getUserById(editedUserId);
        HttpSession currentSession = request.getSession();
        try {
            if (editedUser == null) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
            }
            if (editedUser.getUserRole().equals(UserRole.CLIENT)) {
                ClientService clientService = ServiceFactory.getInstance().getClientService();
                currentSession.setAttribute(EDITED_USER, clientService.getClientById(new Long(editedUserId)));
            } else {
                currentSession.setAttribute(EDITED_USER, editedUser);
            }
            return pagesResourceBundle.getString(EDIT_USER_PAGE);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            currentSession.setAttribute(ERROR_MESSAGE, e);
            return pagesResourceBundle.getString(HOME_PAGE);
        }
    }
}
