package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.finance.FinanceResult;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.Calendar;
import java.util.Set;

public class Event extends Model {
    private Calendar creationDateAndTime;
    private Tournament tournament;
    private Set<Market> markets;
    private EventStatus status;
    private User bookmaker;
    private double margin;
    private int maxWin;
    private boolean isSuspended;
    private FinanceResult financeResult;

    public Event() {

    }

    public Calendar getCreationDateAndTime() {
        return creationDateAndTime;
    }

    public void setCreationDateAndTime(Calendar creationDateAndTime) {
        this.creationDateAndTime = creationDateAndTime;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Set<Market> getMarkets() {
        return markets;
    }

    public void addMarket(Market market) {
        this.markets.add(market);
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public User getBookmaker() {
        return bookmaker;
    }

    public void setBookmaker(User bookmaker) {
        this.bookmaker = bookmaker;
    }


    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public int getMaxWin() {
        return maxWin;
    }

    public void setMaxWin(int maxWin) {
        this.maxWin = maxWin;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public int getNumberOfBets() {
        int numberOfBets = 0;
        for (Market market : this.markets) {
            for (Outcome outcome : market.getOutcomes()) {
                numberOfBets += outcome.getBets().size();
            }
        }
        return numberOfBets;
    }

    public FinanceResult getFinanceResult() {
        if (this.financeResult == null) {
            this.financeResult = new FinanceResult();
        }
        return financeResult;
    }
}
