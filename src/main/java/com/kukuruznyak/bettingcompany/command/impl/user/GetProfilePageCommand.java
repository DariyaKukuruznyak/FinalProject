package com.kukuruznyak.bettingcompany.command.impl.user;

import com.kukuruznyak.bettingcompany.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Command returns a page for editing a client
 */
public class GetProfilePageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
              return pagesResourceBundle.getString(EDIT_PROFILE_PAGE);
     }
}
