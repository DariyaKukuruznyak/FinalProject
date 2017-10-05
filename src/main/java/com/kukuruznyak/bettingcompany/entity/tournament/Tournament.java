package com.kukuruznyak.bettingcompany.entity.tournament;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Tournament extends Model {
    private String name;
    private String country;
    private String score;
    private Date beginningDateAndTime;
    private Collection<Participant> participants;

    public Tournament() {
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return country + ". " + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;

        Tournament that = (Tournament) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getCountry() != null ? !getCountry().equals(that.getCountry()) : that.getCountry() != null) return false;
        if (getScore() != null ? !getScore().equals(that.getScore()) : that.getScore() != null) return false;
        return getBeginningDateAndTime() != null ? getBeginningDateAndTime().equals(that.getBeginningDateAndTime()) : that.getBeginningDateAndTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getScore() != null ? getScore().hashCode() : 0);
        result = 31 * result + (getBeginningDateAndTime() != null ? getBeginningDateAndTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", score='" + score + '\'' +
                ", beginningDateAndTime=" + beginningDateAndTime +
                ", participants=" + participants +
                '}';
    }
}

