package com.kukuruznyak.bettingcompany.command.impl;

import com.kukuruznyak.bettingcompany.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DefaultCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return pagesResourceBundle.getString(HOME_PAGE);
    }
}