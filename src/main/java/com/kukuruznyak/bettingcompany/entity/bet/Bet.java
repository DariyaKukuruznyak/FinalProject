package com.kukuruznyak.bettingcompany.entity.bet;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.bet.ResultOfBet;
import com.kukuruznyak.bettingcompany.entity.bet.TypeOfBet;
import com.kukuruznyak.bettingcompany.entity.event.Outcome;
import com.kukuruznyak.bettingcompany.entity.user.User;

import java.math.BigDecimal;
import java.util.List;

public class Bet extends Model {
    private User client;
    private BigDecimal sumIn;
    private BigDecimal sumOut;
    private TypeOfBet type;
    private ResultOfBet result;
    private List<Outcome> outcomes;
    private String description;

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
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

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void addOutcome(Outcome outcome) {
        this.outcomes.add(outcome);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
