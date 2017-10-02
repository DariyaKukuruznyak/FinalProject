package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddUserPageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
            return pagesResourceBundle.getString("addUser");
    }
}