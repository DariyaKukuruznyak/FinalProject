package com.kukuruznyak.bettingcompany.entity.company;

import com.kukuruznyak.bettingcompany.entity.FinanceResult;
import com.kukuruznyak.bettingcompany.entity.event.Event;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.util.HashSet;
import java.util.Set;

public class Company {
    private String name;
    private Set<User> staff;
    private Set<User> clients;
    private Set<Event> eventsInProgress;
    private Set<Event> eventsArchive;// events which were finished for month ago;
    private FinanceResult generalResult;
    private FinanceResult monthlyResult;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getStaff() {
        return staff;
    }

    public void setStaff(Set<User> staff) {
        this.staff = staff;
    }

    public void addEmployee(User employee) {
        if (this.staff == null) {
            this.staff = new HashSet<User>();
        }
        this.staff.add(employee);
    }

    public Set<User> getClients() {
        return clients;
    }

    public void setClients(Set<User> clients) {
        this.clients = clients;
    }

    public void addClient(User client) {
        if (this.clients == null) {
            this.clients = new HashSet<User>();
        }
        this.clients.add(client);
    }

    public Set<Event> getEventsInProgress() {
        return eventsInProgress;
    }

    public void setEventsInProgress(Set<Event> eventsInProgress) {
        this.eventsInProgress = eventsInProgress;
    }

    public void addEventInProgress(Event event){
        if (this.eventsInProgress == null) {
            this.eventsInProgress = new HashSet<Event>();
        }
        this.eventsInProgress.add(event);
    }

    public Set<Event> getEventsArchive() {
        return eventsArchive;
    }

    public void setEventsArchive(Set<Event> eventsArchive) {
        this.eventsArchive = eventsArchive;
    }

    public void addEventToArchive(Event event) {
        if (this.eventsArchive == null) {
            this.eventsArchive = new HashSet<Event>();
        }
        this.eventsArchive.add(event);
    }

    public FinanceResult getGeneralResult() {
        return generalResult;
    }

    public void setGeneralResult(FinanceResult generalResult) {
        this.generalResult = generalResult;
    }

    public FinanceResult getMonthlyResult() {
        return monthlyResult;
    }

    public void setMonthlyResult(FinanceResult monthlyResult) {
        this.monthlyResult = monthlyResult;
    }
}
