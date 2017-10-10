package com.kukuruznyak.bettingcompany.command.impl.user.authorization;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ClientService;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            UserService userService = serviceFactory.getUserService();
            if (userService.getUserByLogin(request.getParameter(LOGIN)) != null) {
                throw new ApplicationException("User with login '" + request.getParameter(LOGIN) + "' already exist!");
            }
            if (!request.getParameter(PASSWORD).equals(request.getParameter(CONFIRM_PASSWORD))) {
                throw new ApplicationException("Passwords are not equals!");
            }
            Client client = createClient(request);
            if (!userService.isValidUser(client)) {
                throw new ApplicationException("Incorrect user!");
            }
            ClientService clientService = serviceFactory.getClientService();
            client = clientService.add(client);
            currentSession.setAttribute(USER, client);
            LOGGER.info("New authorization " + client.getLogin() + " joined");
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