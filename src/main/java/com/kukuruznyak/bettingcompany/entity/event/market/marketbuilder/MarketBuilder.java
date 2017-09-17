package com.kukuruznyak.bettingcompany.entity.event.market.marketbuilder;

import com.kukuruznyak.bettingcompany.entity.event.market.Market;

public abstract class MarketBuilder {
    protected Market market;

    public void createMarket() {
        this.market = new Market();
    }

    public abstract void buildName();

    public abstract void buildChangeable();

    public Market build() {
        return this.market;
    }
}
