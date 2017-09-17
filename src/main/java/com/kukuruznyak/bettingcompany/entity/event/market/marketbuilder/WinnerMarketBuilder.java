package com.kukuruznyak.bettingcompany.entity.event.market.marketbuilder;

import com.kukuruznyak.bettingcompany.entity.enums.MarketNames;

public class WinnerMarketBuilder extends MarketBuilder {

    public void buildName() {
        this.market.setName(MarketNames.WINNER);
    }

    public void buildChangeable() {
        this.market.setChangeable(true);
    }
}
