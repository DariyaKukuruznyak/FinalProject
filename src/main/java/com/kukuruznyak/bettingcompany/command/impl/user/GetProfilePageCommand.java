package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetProfilePageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = serviceFactory.getUserService();
        User editedUser = userService.getUserById(request.getParameter(ID));
        HttpSession currentSession = request.getSession();
        try {
            if (editedUser == null) {
                throw new ApplicationException("Unexpected request!");
            }
            currentSession.setAttribute(EDITED_USER, editedUser);
            return pagesResourceBundle.getString(EDIT_USER_PAGE);
        } catch (ApplicationException e) {
            LOGGER.error(e);
            currentSession.setAttribute(ERROR_MESSAGE, e);
            return pagesResourceBundle.getString(HOME_PAGE);
        }
    }

}
