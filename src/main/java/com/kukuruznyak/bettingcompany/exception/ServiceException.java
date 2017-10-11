package com.kukuruznyak.bettingcompany.exception;

import java.sql.SQLException;

public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(SQLException e) {

    }

    public ServiceException(Exception e) {

    }
}
