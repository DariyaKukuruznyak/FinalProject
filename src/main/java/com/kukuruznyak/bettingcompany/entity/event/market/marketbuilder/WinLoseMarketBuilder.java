package com.kukuruznyak.bettingcompany.entity.event.market.marketbuilder;

import com.kukuruznyak.bettingcompany.entity.enums.MarketNames;

public class WinLoseMarketBuilder extends MarketBuilder {

    public void buildName() {
        this.market.setName(MarketNames.WIN_LOSE);
    }

    public void buildChangeable() {
        this.market.setChangeable(false);
    }
}
