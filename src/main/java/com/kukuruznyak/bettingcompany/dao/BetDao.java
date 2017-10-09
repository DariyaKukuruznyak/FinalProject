package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.bet.Bet;

import java.util.Collection;
import java.util.List;

public interface BetDao extends AbstractDao<Bet> {
    Collection<Bet> getByUserId(Long clientId);
}
