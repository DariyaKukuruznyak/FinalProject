package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProfilePageCommand extends Command {
    private UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        User editedUser = userService.getUserById(request.getParameter("id"));
        try {
            if (editedUser == null) {
                throw new ApplicationException("Unexpected request!");
            }
            request.getSession().setAttribute("editedUser", editedUser);
            return pagesResourceBundle.getString("editUser");
        } catch (ApplicationException e) {
            LOGGER.error(e);
            request.getSession().setAttribute("errorMessage", e);
            return pagesResourceBundle.getString("home");
        }
    }

}
