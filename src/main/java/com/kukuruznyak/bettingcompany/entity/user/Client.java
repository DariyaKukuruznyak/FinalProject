package com.kukuruznyak.bettingcompany.entity.user;

import com.kukuruznyak.bettingcompany.entity.Bet;
import com.kukuruznyak.bettingcompany.entity.enums.UserRole;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Client extends User {
    private double maxBet;
    private BigDecimal balance;
    private Set<Bet> bets;
    private String description;

    public Client() {
        this.userRole = UserRole.PLAYER;
    }

    public double getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(double maxBet) {
        this.maxBet = maxBet;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<Bet> getBets() {
        return bets;
    }

    public void addBet(Bet bet) {
        if (this.bets == null) {
            this.bets = new HashSet<Bet>();
        }
        this.bets.add(bet);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
