package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Event extends Model {
    private Date creationDate;
    private Tournament tournament;
    private Collection<Market> markets;
    private EventStatus status;
    private User bookmaker;
    private boolean isSuspended;
    private BigDecimal turnover;
    private BigDecimal profit;

    public Event() {    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Collection<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(Collection<Market> markets) {
        this.markets = markets;
    }

    public void addMarket(Market market) {
        if (this.markets == null) {
            this.markets = new ArrayList<>();
        }
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

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (isSuspended() != event.isSuspended()) return false;
        if (!getCreationDate().equals(event.getCreationDate())) return false;
        if (!getTournament().equals(event.getTournament())) return false;
        if (getStatus() != event.getStatus()) return false;
        return getBookmaker().equals(event.getBookmaker());
    }

    @Override
    public int hashCode() {
        int result = getCreationDate().hashCode();
        result = 31 * result + getTournament().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getBookmaker().hashCode();
        result = 31 * result + (isSuspended() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", status=" + status +
                ", bookmakerId=" + bookmaker.getFullName() +
                ", isSuspended=" + isSuspended +
                '}';
    }
}
