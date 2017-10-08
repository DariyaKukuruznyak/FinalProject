package com.kukuruznyak.bettingcompany.entity.bet;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Bet extends Model {
    private User client;
    private Date date;
    private BigDecimal sumIn;
    private BigDecimal sumOut;
    private TypeOfBet type;
    private ResultOfBet result;
    private Collection<BetItem> items;
    private double totalCoefficient;
    private String description;

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getSumIn() {
        return sumIn;
    }

    public void setSumIn(BigDecimal sumIn) {
        this.sumIn = sumIn;
    }

    public TypeOfBet getType() {
        return type;
    }

    public BigDecimal getSumOut() {
        return sumOut;
    }

    public void setSumOut(BigDecimal sumOut) {
        this.sumOut = sumOut;
    }

    public void setType(TypeOfBet type) {
        this.type = type;
    }

    public ResultOfBet getResult() {
        return result;
    }

    public void setResult(ResultOfBet result) {
        this.result = result;
    }

    public Collection<BetItem> getItems() {
        return items;
    }

    public void setItems(Collection<BetItem> items) {
        this.items = items;
    }

    public void addItems(BetItem item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalCoefficient() {
        return totalCoefficient;
    }

    public void setTotalCoefficient(double totalCoefficient) {
        this.totalCoefficient = totalCoefficient;
    }
}
