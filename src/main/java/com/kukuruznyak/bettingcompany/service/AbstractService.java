package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.entity.bet.Bet;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public abstract class AbstractService {
    protected static final Logger LOGGER = Logger.getLogger(AbstractService.class);
    protected static DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL);
    protected static ResourceBundle validationBundle = ResourceBundle.getBundle("validationPatterns");
}
