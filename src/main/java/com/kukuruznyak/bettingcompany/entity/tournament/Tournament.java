package com.kukuruznyak.bettingcompany.entity.tournament;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Tournament extends Model {
    private String name;
    private String winner;
    private Date beginningDateAndTime;
    private Collection<Participant> participants;

    public Tournament() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Collection<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipant(Participant participant) {
        if (this.participants == null) {
            this.participants = new HashSet<>();
        }
        this.participants.add(participant);
    }

    public Date getBeginningDateAndTime() {
        return beginningDateAndTime;
    }

    public void setBeginningDateAndTime(Date beginningDateAndTime) {
        this.beginningDateAndTime = beginningDateAndTime;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getNumberOfParticipants() {
        if (this.participants != null) {
            return this.participants.size();
        } else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;

        Tournament that = (Tournament) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
             if (getWinner() != null ? !getWinner().equals(that.getWinner()) : that.getWinner() != null) return false;
        return getBeginningDateAndTime() != null ? getBeginningDateAndTime().equals(that.getBeginningDateAndTime()) : that.getBeginningDateAndTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getWinner() != null ? getWinner().hashCode() : 0);
        result = 31 * result + (getBeginningDateAndTime() != null ? getBeginningDateAndTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", winner='" + winner + '\'' +
                ", beginningDateAndTime=" + beginningDateAndTime +
                ", participants=" + participants +
                '}';
    }
}

