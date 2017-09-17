package com.kukuruznyak.bettingcompany.entity;

import com.kukuruznyak.bettingcompany.entity.enums.ResultOfBet;
import com.kukuruznyak.bettingcompany.entity.enums.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.event.market.Outcome;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.List;

public class Bet {
    private User player;
    private double sum;
    private TypeOfBet type;
    private ResultOfBet result;
    private List<Outcome> markets;
    private String description;

}
