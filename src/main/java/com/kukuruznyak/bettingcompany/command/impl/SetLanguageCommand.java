package com.kukuruznyak.bettingcompany.command.impl;


import com.kukuruznyak.bettingcompany.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetLanguageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter(LANG);
        if (lang.equals(RU_LANG)) {
            request.getSession().setAttribute(LANG, RU_LANG);
        } else {
            request.getSession().setAttribute(LANG, EN_LANG);
        }
        return pagesResourceBundle.getString(HOME_PAGE);
    }
}
