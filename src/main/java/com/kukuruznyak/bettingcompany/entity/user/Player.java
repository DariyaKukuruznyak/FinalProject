package com.kukuruznyak.bettingcompany.entity.user;

import com.kukuruznyak.bettingcompany.entity.enums.UserRole;

import java.math.BigDecimal;

public class Player extends User {
    private double maxBet;
    private BigDecimal balance;
    public Player() {
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
}
