package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.OutcomeDao;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class OutcomeService extends AbstractService {
    private static OutcomeService instance;

    public static OutcomeService getInstance() {
        if (instance == null) {
            synchronized (OutcomeService.class) {
                if (instance == null) {
                    instance = new OutcomeService();
                }
            }
        }
        return instance;
    }

    private OutcomeService() {
    }

    public void update(Outcome outcome) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                OutcomeDao outcomeDao = daoFactory.getOutcomeDao(connection);
                outcomeDao.update(outcome);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Outcome add(Outcome outcome) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                OutcomeDao outcomeDao = daoFactory.getOutcomeDao(connection);
                return outcomeDao.add(outcome);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    public Outcome getById(String outcomeId) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                OutcomeDao outcomeDao = daoFactory.getOutcomeDao(connection);
                return outcomeDao.getById(new Long(outcomeId));
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}
