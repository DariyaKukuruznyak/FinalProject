package com.kukuruznyak.bettingcompany.command;

import com.kukuruznyak.bettingcompany.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public abstract class Command {

    protected static ResourceBundle resourceBundle = ResourceBundle.getBundle("pages");

    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException;
}
