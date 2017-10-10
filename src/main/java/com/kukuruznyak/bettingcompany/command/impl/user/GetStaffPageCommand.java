package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.PageNameConstants;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class GetStaffPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession currentSession = request.getSession();
        User authorizedUser = (User) currentSession.getAttribute(USER);
        if (authorizedUser == null || !authorizedUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            LOGGER.error("Unexpected request!");
            throw new ApplicationException("Unexpected request!");
        }
        UserService userService = serviceFactory.getUserService();
        Collection<User> staff = userService.getStaff();
        currentSession.setAttribute(USERS, staff);
        return pagesResourceBundle.getString(USER_LIST_PAGE);
    }
}
