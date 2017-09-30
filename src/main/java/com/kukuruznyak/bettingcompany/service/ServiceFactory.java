package com.kukuruznyak.bettingcompany.service;

public class ServiceFactory {
    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (ServiceFactory.class) {
                if (instance == null) {
                    instance = new ServiceFactory();
                }
            }
        }
        return instance;
    }

    private ServiceFactory() {
    }
}
