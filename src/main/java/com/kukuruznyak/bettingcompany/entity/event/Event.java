package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.company.FinanceResult;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Event extends Model {
    private Calendar creationDateAndTime;
    private Calendar beginningDateAndTime;
    private Tournament tournament;
    private Set<Participant> participants;
    private Set<Market> markets;
    private EventStatus status;
    private User bookmaker;
    private String score;
    private double margin;
    private double maxWin;
    private boolean isSuspended;
    private FinanceResult financeResult;

    public Event() {
        this.participants = new HashSet<Participant>();
        this.markets = new HashSet<Market>();
        this.status = EventStatus.NOT_STARTED;
        this.isSuspended = false;
    }

    public Calendar getCreationDateAndTime() {
        return creationDateAndTime;
    }

    public void setCreationDateAndTime(Calendar creationDateAndTime) {
        this.creationDateAndTime = creationDateAndTime;
    }

    public Calendar getBeginningDateAndTime() {
        return beginningDateAndTime;
    }

    public void setBeginningDateAndTime(Calendar beginningDateAndTime) {
        this.beginningDateAndTime = beginningDateAndTime;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
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
}
