package com.kukuruznyak.bettingcompany.service;

import com.kukuruznyak.bettingcompany.dao.OutcomeDao;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;

public class OutcomeService extends AbstractService {
    private static OutcomeService instance;
    private OutcomeDao outcomeDao = daoFactory.getOutcomeDao();

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
        outcomeDao.update(outcome);
    }

    public Outcome getById(String outcomeId) {
        return outcomeDao.getById(new Long(outcomeId));
    }
}
