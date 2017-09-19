package com.kukuruznyak.bettingcompany.entity;

import java.math.BigDecimal;

public class FinanceResult {
    private BigDecimal turnover;
    private BigDecimal profit;
    private double percentageOfProfit;
    private long numberOfBets;

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
        recalculatePercentage();
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
        recalculatePercentage();
    }

    public double getPercentageOfProfit() {
        return percentageOfProfit;
    }

    public long getNumberOfBets() {
        return numberOfBets;
    }

    public void setNumberOfBets(long numberOfBets) {
        this.numberOfBets = numberOfBets;
    }

    private void recalculatePercentage() {
        this.percentageOfProfit = this.profit.divide(this.turnover).doubleValue();
    }
}