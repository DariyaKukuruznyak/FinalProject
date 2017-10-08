package com.kukuruznyak.bettingcompany.controller;

import com.kukuruznyak.bettingcompany.command.Invoker;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

public class MainServlet extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(MainServlet.class);

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
        currentSession.removeAttribute("errorMessage");
        currentSession.removeAttribute("successMessage");
        String page;
        try {
            Invoker commandInvoker = Invoker.getInstance();
            page = commandInvoker.invoke(request, response);
        } catch (RuntimeException e) {
            LOGGER.error("Page isn't identified. URI: " + request.getRequestURI() + " Method: " + request.getMethod());
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute("errorMessage", e.getMessage());
            page = ResourceBundle.getBundle("pages").getString("error");
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}
