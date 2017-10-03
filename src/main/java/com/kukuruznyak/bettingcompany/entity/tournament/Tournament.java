package com.kukuruznyak.bettingcompany.entity.tournament;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tournament extends Model {
    private String name;
    private String country;
    private String score;
    private Date beginningDateAndTime;
    private List<Participant> participants;

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

    public List<Participant> getParticipants() {
        return participants;
    }

    public void addParticipant(Participant participant) {
        if (this.participants == null) {
            this.participants = new ArrayList<>();
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
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", score='" + score + '\'' +
                ", beginningDateAndTime=" + beginningDateAndTime +
                ", participants=" + participants +
                '}';
    }
}

