package com.kukuruznyak.bettingcompany.command.impl;


import com.kukuruznyak.bettingcompany.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetLanguageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter("lang");
        if (lang.equals("en_US")) {
            request.getSession().setAttribute("language", "en_US");
        } else {
            request.getSession().setAttribute("language", "ru_RU");
        }
        return pagesResourceBundle.getString("home");
    }
}
