package com.kukuruznyak.bettingcompany.controller;

import com.kukuruznyak.bettingcompany.command.Invoker;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

public class MainServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainServlet.class);
    private static final Invoker commandInvoker = Invoker.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestHandler(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestHandler(request, response);
    }

    private void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        System.out.println("MainServlet");
        try {
            page = commandInvoker.invoke(request, response);
            LOGGER.info("Page " + page + " opened");
        } catch (RuntimeException e) {

            LOGGER.error("Page isn't identified");
            LOGGER.error(e.getMessage());
            page = ResourceBundle.getBundle("pages").getString("error");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
