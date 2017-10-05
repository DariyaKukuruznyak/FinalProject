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

public class RegisterCommand extends Command {
    private ClientService clientService = ServiceFactory.getInstance().getClientService();
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        try {
            if (userService.getUserByLogin(request.getParameter("login")) != null) {
                throw new ApplicationException("User with login '" + request.getParameter("login") + "' already exist!");
            }
            if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
                throw new ApplicationException("Passwords are not equals!");
            }
            Client client = createClient(request);
            if (!userService.isValidUser(client)) {
                throw new ApplicationException("Incorrect user!");
            }
            client = clientService.add(client);
            request.getSession().setAttribute("user", client);
            LOGGER.info("New authorization " + client.getLogin() + " joined");
            return pagesResourceBundle.getString("home");
        } catch (ApplicationException e) {
            request.getSession().setAttribute("errorMessage", e.getMessage());
            LOGGER.error(e.getMessage());
            return pagesResourceBundle.getString("register");
        }
    }


    private Client createClient(HttpServletRequest request) {
        User user = new UserBuilder()
                .buildFirstName(request.getParameter("firstName"))
                .buildLastName(request.getParameter("lastName"))
                .buildLogin(request.getParameter("login"))
                .buildEmail(request.getParameter("email"))
                .buildPassword(request.getParameter("password"))
                .buildUserRole(UserRole.CLIENT)
                .build();
        return new Client(user);
    }
}