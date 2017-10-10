package com.kukuruznyak.bettingcompany.command;

import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;
import com.kukuruznyak.bettingcompany.util.StringMessages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public abstract class Command implements RequestAttributeConstants, PageNameConstants, StringMessages {
    protected static Logger LOGGER = Logger.getLogger(Command.class);
    protected static ResourceBundle pagesResourceBundle = ResourceBundle.getBundle("pages");
    protected static ServiceFactory serviceFactory = ServiceFactory.getInstance();

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}
