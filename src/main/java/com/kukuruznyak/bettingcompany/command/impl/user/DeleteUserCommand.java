package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;
import com.kukuruznyak.bettingcompany.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteUserCommand extends Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        userService.delete(request.getParameter("id"));
        List<?> users = null;
        User authorizedUser = (User) request.getSession().getAttribute("user");
        if (authorizedUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            users = userService.getStaff();
        }
        if (authorizedUser.getUserRole().equals(UserRole.RISK_CONTROLLER)) {
            users = userService.getAllClients();
        }
        request.getSession().setAttribute("users", users);
        return pagesResourceBundle.getString("userList");
    }
}
