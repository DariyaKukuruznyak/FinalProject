package com.kukuruznyak.bettingcompany.entity.user;

import com.kukuruznyak.bettingcompany.entity.bet.Bet;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Client extends User implements RegistrationBonus {
    private int maxBet;
    private BigDecimal balance = REGISTRATION_BONUS;
    private Collection<Bet> bets;
    private String description;

    public Client() {
    }

    public Client(User user) {
        super(user);
    }

    public int getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(int maxBet) {
        this.maxBet = maxBet;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Collection<Bet> getBets() {
        return bets;
    }

    public void addBet(Bet bet) {
        if (this.bets == null) {
            this.bets = new LinkedList<>();
        }
        this.bets.add(bet);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Client{" +
                "maxBet=" + maxBet +
                ", balance=" + balance +
                ", bets=" + bets +
                ", description='" + description + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                ", userRole=" + userRole +
                ", id=" + id +
                '}';
    }
}
