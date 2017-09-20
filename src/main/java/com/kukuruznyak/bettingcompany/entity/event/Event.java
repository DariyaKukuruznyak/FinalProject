package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Bet;
import com.kukuruznyak.bettingcompany.entity.FinanceResult;
import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.enums.EventStatus;
import com.kukuruznyak.bettingcompany.entity.event.market.Market;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Event extends Model {
    private Calendar openDateAndTime;
    private Calendar startDateAndTime;
    private String country;
    private String tournament;
    private Set<Participant> participants;
    private Set<Market> markets;
    private EventStatus status;
    private User bookmaker;
    private String score;
    private double margin;
    private double maxWin;
    private boolean isSuspended;
    private FinanceResult financeResult;
    private Set<Bet> bets;

    public Event() {
        this.participants = new HashSet<Participant>();
        this.markets = new HashSet<Market>();
        this.status = EventStatus.NOT_STARTED;
        this.isSuspended = false;
    }

    public Calendar getOpenDateAndTime() {
        return openDateAndTime;
    }

    public void setOpenDateAndTime(Calendar openDateAndTime) {
        this.openDateAndTime = openDateAndTime;
    }

    public Calendar getStartDateAndTime() {
        return startDateAndTime;
    }

    public void setStartDateAndTime(Calendar startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void addParticipant(Participant participant) {
        if (this.participants == null) {
            this.participants = new HashSet<Participant>();
        }
        this.participants.add(participant);
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getMaxWin() {
        return maxWin;
    }

    public void setMaxWin(double maxWin) {
        this.maxWin = maxWin;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public FinanceResult getFinanceResult() {
        if (this.financeResult == null) {
            this.financeResult = new FinanceResult();
        }
        return financeResult;
    }

    public void addBet(Bet bet) {
        if (this.bets == null) {
            this.bets = new HashSet<Bet>();
        }
        this.bets.add(bet);
    }

    public Set<Bet> getBets() {
        return bets;
    }
}
