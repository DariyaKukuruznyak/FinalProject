package com.kukuruznyak.bettingcompany.entity.event;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.Collection;
import java.util.Date;

public class Event extends Model {
    private Date creationDateAndTime;
    private Tournament tournament;
    private Collection<Market> markets;
    private EventStatus status;
    private User bookmaker;
    private boolean isSuspended;
    //    private FinanceResult financeResultSingle;
    //    private FinanceResult financeResultExpress;
    public Event() {

    }

    public Date getCreationDateAndTime() {
        return creationDateAndTime;
    }

    public void setCreationDateAndTime(Date creationDateAndTime) {
        this.creationDateAndTime = creationDateAndTime;
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
//        int numberOfBets = 0;
//        for (Market market : this.markets) {
//            for (Outcome outcome : market.getOutcomes()) {
//                numberOfBets += outcome.getBets().size();
//            }
//        }
//        return numberOfBets;
        return 50;//TODO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (isSuspended() != event.isSuspended()) return false;
        if (!getCreationDateAndTime().equals(event.getCreationDateAndTime())) return false;
        if (!getTournament().equals(event.getTournament())) return false;
        if (getStatus() != event.getStatus()) return false;
        return getBookmaker().equals(event.getBookmaker());
    }

    @Override
    public int hashCode() {
        int result = getCreationDateAndTime().hashCode();
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
                ", creationDateAndTime=" + creationDateAndTime +
                ", status=" + status +
                ", bookmaker=" + bookmaker.getFullName() +
                ", isSuspended=" + isSuspended +
                '}';
    }
}
