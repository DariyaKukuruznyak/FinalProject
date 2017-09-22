package com.kukuruznyak.bettingcompany.entity.event.market;

import com.kukuruznyak.bettingcompany.entity.Model;
import com.kukuruznyak.bettingcompany.entity.enums.MarketNames;

import java.util.Set;

public class Market extends Model {
    private MarketNames name;
    private Set<MarketItem> marketItems;
    private boolean isChangeable = true;

    public MarketNames getName() {
        return name;
    }

    public void setName(MarketNames name) {
        this.name = name;
    }

    public Set<MarketItem> getMarketItems() {
        return marketItems;
    }

    public void addItem(MarketItem marketItem) {
        if (this.marketItems.contains(marketItem)) {
            return;
        }
        this.marketItems.add(marketItem);
    }

    public boolean isChangeable() {
        return isChangeable;
    }

    public void setChangeable(boolean isChangeable) {
        this.isChangeable = isChangeable;
    }
}
