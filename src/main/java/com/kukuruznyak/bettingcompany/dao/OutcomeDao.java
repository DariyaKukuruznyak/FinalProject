package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.event.Outcome;

import java.util.Collection;

public interface OutcomeDao extends AbstractDao<Outcome> {
    Collection<Outcome> getAllByMarketId(Long marketId);
}
