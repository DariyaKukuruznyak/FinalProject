package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.event.Market;

import java.util.Collection;

public interface MarketDao extends AbstractDao<Market> {
    Collection<Market> getAllByEventId(Long id);
}
