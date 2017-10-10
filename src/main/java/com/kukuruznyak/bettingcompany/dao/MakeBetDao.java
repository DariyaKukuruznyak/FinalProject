package com.kukuruznyak.bettingcompany.dao;

import com.kukuruznyak.bettingcompany.entity.bet.Bet;

public interface MakeBetDao {
    boolean makeBet(Bet bet);
}
