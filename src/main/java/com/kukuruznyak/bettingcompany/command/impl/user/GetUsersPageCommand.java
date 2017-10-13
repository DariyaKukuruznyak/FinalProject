package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.Client;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ClientService;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
/**
 * Command returns a page of users list
 */
public class GetUsersPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User authorizedUser = (User) currentSession.getAttribute(USER);
        if (authorizedUser == null) {
            LOGGER.error(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
            throw new ApplicationException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
        }
        switch (authorizedUser.getUserRole()) {
            case ADMINISTRATOR:
                UserService userService = serviceFactory.getUserService();
                Collection<User> staff = userService.getStaff();
                currentSession.setAttribute(USERS, staff);
                break;
            case RISK_CONTROLLER:
                ClientService clientService = serviceFactory.getClientService();
                Collection<Client> clients = clientService.getAllClients();
                currentSession.setAttribute(USERS, clients);
                break;
            default:
                LOGGER.error(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
                throw new ApplicationException(StringMessages.getMessage(StringMessages.UNEXPECTED_REQUEST));
        }
        return pagesResourceBundle.getString(USER_LIST_PAGE);
    }
}
