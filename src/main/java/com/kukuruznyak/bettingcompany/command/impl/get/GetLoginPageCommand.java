package com.kukuruznyak.bettingcompany.command.impl.get;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLoginPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        return pagesResourceBundle.getString("login");
    }
}
