package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            UserService userService = serviceFactory.getUserService();
            if (userService.getUserByLogin(request.getParameter(LOGIN)) != null) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.USER_EXIST )+ request.getParameter(LOGIN));
            }
            User user = fillUser(request);
            if (!userService.isValidUser(user)) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_USER));
            }
            userService.add(user);
            currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.USER_CREATED_SUCCESSFULLY));
            return pagesResourceBundle.getString(ADD_USER_PAGE);
        } catch (ApplicationException e) {
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
            LOGGER.error(e.getMessage());
            return pagesResourceBundle.getString(ADD_USER_PAGE);
        }
    }

    private User fillUser(HttpServletRequest request) {
        return new UserBuilder()
                .buildFirstName(request.getParameter(FIRST_NAME))
                .buildLastName(request.getParameter(LAST_NAME))
                .buildLogin(request.getParameter(LOGIN))
                .buildEmail(request.getParameter(EMAIL))
                .buildPassword(request.getParameter(PASSWORD))
                .buildUserRole(UserRole.valueOf(request.getParameter(USER_ROLE)))
                .build();
    }
}
