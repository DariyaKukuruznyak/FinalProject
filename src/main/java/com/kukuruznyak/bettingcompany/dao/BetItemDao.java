package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.bet.BetItem;

import java.util.Collection;

public interface BetItemDao extends AbstractDao<BetItem> {
    Collection<BetItem> getAllByBetId(Long id);

    Collection<BetItem> getAllByOutcomeId(Long id);

}
