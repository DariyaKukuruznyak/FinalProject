package com.kukuruznyak.bettingcompany.command.impl.authorization;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.command.impl.HomeCommand;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        LOGGER.info("User " + request.getAttribute("userName") + " logged out");
        request.getSession().invalidate();
        return new HomeCommand().execute(request, response);
    }
}
