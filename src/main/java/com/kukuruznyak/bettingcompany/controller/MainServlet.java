package com.kukuruznyak.bettingcompany.controller;

import com.kukuruznyak.bettingcompany.command.Invoker;
import com.kukuruznyak.bettingcompany.util.StringMessages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

import static com.kukuruznyak.bettingcompany.command.PageNameConstants.ERROR_PAGE;
import static com.kukuruznyak.bettingcompany.command.RequestAttributeConstants.ERROR_MESSAGE;
import static com.kukuruznyak.bettingcompany.command.RequestAttributeConstants.SUCCESS_MESSAGE;

/**
 * Main servlet
 */
public class MainServlet extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(MainServlet.class);
    private static final String PAGE_RESOURCE_BUNDLE = "pages";

    public MainServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestHandler(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestHandler(request, response);
    }

    private void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        currentSession.removeAttribute(ERROR_MESSAGE);
        currentSession.removeAttribute(SUCCESS_MESSAGE);
        String page;
        try {
            Invoker commandInvoker = Invoker.getInstance();
            page = commandInvoker.invoke(request, response);
        } catch (RuntimeException e) {
            LOGGER.error(StringMessages.getMessage(StringMessages.NO_PAGE_IDENTIFIED) + request.getRequestURI());
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute(ERROR_MESSAGE, e.getMessage());
            page = ResourceBundle.getBundle(PAGE_RESOURCE_BUNDLE).getString(ERROR_PAGE);
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}
