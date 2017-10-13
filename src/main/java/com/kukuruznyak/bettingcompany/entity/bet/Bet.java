package com.kukuruznyak.bettingcompany.entity.bet;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.user.Client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Bet extends Model {
    private Long clientId;
    private Date date;
    private BigDecimal sumIn;
    private BigDecimal sumOut;
    private TypeOfBet type;
    private ResultOfBet result;
    private Collection<BetItem> items;
    private double totalCoefficient;
    private String description;

    public Bet() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    @Override
    public String toString() {
        return "Bet{" +
                "clientId=" + clientId +
                ", date=" + date +
                ", sumIn=" + sumIn +
                ", sumOut=" + sumOut +
                ", type=" + type +
                ", result=" + result +
                ", items=" + items +
                ", totalCoefficient=" + totalCoefficient +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bet)) return false;

        Bet bet = (Bet) o;

        if (Double.compare(bet.getTotalCoefficient(), getTotalCoefficient()) != 0) return false;
        if (!getClientId().equals(bet.getClientId())) return false;
        if (!getDate().equals(bet.getDate())) return false;
        if (!getSumIn().equals(bet.getSumIn())) return false;
        if (!getSumOut().equals(bet.getSumOut())) return false;
        if (getType() != bet.getType()) return false;
        return getResult() == bet.getResult();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getClientId().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getSumIn().hashCode();
        result = 31 * result + getSumOut().hashCode();
        result = 31 * result + getType().hashCode();
        temp = Double.doubleToLongBits(getTotalCoefficient());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
