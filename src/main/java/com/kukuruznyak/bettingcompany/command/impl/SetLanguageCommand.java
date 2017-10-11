package com.kukuruznyak.bettingcompany.command.impl;


import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.util.StringMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.kukuruznyak.bettingcompany.util.StringMessages.ENGLISH_LOCALE;
import static com.kukuruznyak.bettingcompany.util.StringMessages.RUSSIAN_LOCALE;

public class SetLanguageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter(LANG);
        if (lang.equals(RU_LANG)) {
            request.getSession().setAttribute(LANG, RU_LANG);
            StringMessages.setLocale(RUSSIAN_LOCALE);
        } else {
            request.getSession().setAttribute(LANG, EN_LANG);
            StringMessages.setLocale(ENGLISH_LOCALE);
        }
        return pagesResourceBundle.getString(HOME_PAGE);
    }
}
