package com.kukuruznyak.bettingcompany.command.impl.user.authorization;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ClientService;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command for registration of user
 */
public class RegisterCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        String login = request.getParameter(LOGIN);
        if (login == null) {
            throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_INPUT));
        }
        try {
            UserService userService = serviceFactory.getUserService();
            if (userService.getUserByLogin(login) != null) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.USER_EXIST) + request.getParameter(LOGIN));
            }
            if (!request.getParameter(PASSWORD).equals(request.getParameter(CONFIRM_PASSWORD))) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.PASSWORDS_NOT_EXIST));
            }
            Client client = createClient(request);
            if (!userService.isValidUser(client)) {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_USER));
            }
            ClientService clientService = serviceFactory.getClientService();
            client = clientService.add(client);
            currentSession.setAttribute(USER, client);
            LOGGER.info(StringMessages.getMessage(StringMessages.CLIENT_JOINED) + client.getLogin());
            return pagesResourceBundle.getString(HOME_PAGE);
        } catch (ApplicationException e) {
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
            LOGGER.error(e.getMessage());
            return pagesResourceBundle.getString(REGISTER_PAGE);
        }
    }

    private Client createClient(HttpServletRequest request) {
        User user = new UserBuilder()
                .buildFirstName(request.getParameter(FIRST_NAME))
                .buildLastName(request.getParameter(LAST_NAME))
                .buildLogin(request.getParameter(LOGIN))
                .buildEmail(request.getParameter(EMAIL))
                .buildPassword(request.getParameter(PASSWORD))
                .buildUserRole(UserRole.CLIENT)
                .build();
        return new Client(user);
    }
}