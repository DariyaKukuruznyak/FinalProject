package com.kukuruznyak.bettingcompany.entity.event.market.marketbuilder;

import com.kukuruznyak.bettingcompany.entity.event.market.Market;

public class MarketBuilderDirector {
   private MarketBuilder builder;

    public Market buildMarket() {
        builder.createMarket();
        builder.buildChangeable();
        builder.buildChangeable();
        return builder.build();
    }

    public void setBuilder(MarketBuilder builder) {
        this.builder = builder;
    }
}
