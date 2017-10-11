package com.kukuruznyak.bettingcompany.command.impl.user.authorization;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.impl.HomeCommand;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info(StringMessages.getMessage(StringMessages.USER_LOGGED_OUT) +
                ((User) request.getSession().getAttribute(USER)).getFullName());
        request.getSession().invalidate();
        return new HomeCommand().execute(request, response);
    }
}
