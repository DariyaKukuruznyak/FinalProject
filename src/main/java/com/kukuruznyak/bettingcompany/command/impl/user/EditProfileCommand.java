package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ClientService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProfileCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        try {
            ClientService clientService = serviceFactory.getClientService();
            Client client = (Client) currentSession.getAttribute(USER);
            client = editProfile(request, client);
            if (ServiceFactory.getInstance().getUserService().isValidUser(client)) {
                clientService.update(client);
                currentSession.setAttribute(SUCCESS_MESSAGE, StringMessages.getMessage(StringMessages.USER_UPDATED_SUCCESSFULLY));
                currentSession.setAttribute(USER, client);
            } else {
                throw new ApplicationException(StringMessages.getMessage(StringMessages.INCORRECT_USER));
            }
            return pagesResourceBundle.getString(EDIT_PROFILE_PAGE);
        } catch (ApplicationException e) {
            currentSession.setAttribute(ERROR_MESSAGE, e.getMessage());
            LOGGER.error(e.getMessage());
            return pagesResourceBundle.getString(EDIT_PROFILE_PAGE);
        }
    }

    private Client editProfile(HttpServletRequest request, Client client) {
        client.setFirstName(request.getParameter(FIRST_NAME));
        client.setLastName(request.getParameter(LAST_NAME));
        client.setEmail(request.getParameter(EMAIL));
        String description = request.getParameter(DESCRIPTION);
        if (description != null) {
            client.setDescription(description);
        }
        String maxBet = request.getParameter(MAX_BET);
        if (maxBet != null && maxBet.matches("[0-9]*"))
            client.setMaxBet(new Integer(maxBet));
        return client;
    }
}