package com.kukuruznyak.bettingcompany.command;

import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public abstract class Command {
    protected static Logger LOGGER=Logger.getLogger(Command.class);
    protected static ResourceBundle pagesResourceBundle = ResourceBundle.getBundle("pages");

    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException;
}
