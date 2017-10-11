package com.kukuruznyak.bettingcompany.command.impl.user.authorization;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.RequestAttributeConstants;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN);
        UserService userService = serviceFactory.getUserService();
        HttpSession currentSession = request.getSession();
        if (userService.isUserExist(login, request.getParameter(PASSWORD))) {
            User user = userService.getUserByLogin(login);
            if (user.getUserRole().equals(UserRole.CLIENT)) {
                Client client = ServiceFactory.getInstance().getClientService().getClientById(user.getId());
                currentSession.setAttribute(USER, client);
            } else {
                currentSession.setAttribute(USER, user);
            }
            LOGGER.info(StringMessages.getMessage(StringMessages.USER_SIGNED_IN) + login);
            currentSession.setAttribute(RequestAttributeConstants.ADMIN_ROLE, UserRole.ADMINISTRATOR);
            currentSession.setAttribute(BOOKMAKER_ROLE, UserRole.BOOKMAKER);
            currentSession.setAttribute(RISK_CONTROLLER_ROLE, UserRole.RISK_CONTROLLER);
            currentSession.setAttribute(CLIENT_ROLE, UserRole.CLIENT);
            return pagesResourceBundle.getString(HOME_PAGE);
        } else {
            currentSession.setAttribute(ERROR_MESSAGE, StringMessages.getMessage(StringMessages.INCORRECT_INPUT));
            return pagesResourceBundle.getString(LOGIN_PAGE);
        }
    }
}
