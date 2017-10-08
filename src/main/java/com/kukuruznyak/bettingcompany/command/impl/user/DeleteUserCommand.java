package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.ClientService;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class DeleteUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        userService.delete(request.getParameter("id"));
        Collection<?> users = null;
        User authorizedUser = (User) request.getSession().getAttribute("user");
        if (authorizedUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            users = userService.getStaff();
        }
        if (authorizedUser.getUserRole().equals(UserRole.RISK_CONTROLLER)) {
            ClientService clientService = ServiceFactory.getInstance().getClientService();
            users = clientService.getAllClients();
        }
        request.getSession().setAttribute("users", users);
        return pagesResourceBundle.getString("userList");
    }
}
