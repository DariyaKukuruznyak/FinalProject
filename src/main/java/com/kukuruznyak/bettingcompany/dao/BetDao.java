package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.bet.Bet;

import java.util.Collection;

public interface BetDao extends AbstractDao<Bet> {
    Collection<Bet> getByClientId(Long clientId);

    Collection<Bet> getByOutcomeId(Long outcomeId);

    Long getRowNumber();
}