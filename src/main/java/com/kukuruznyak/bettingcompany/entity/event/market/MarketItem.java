package com.kukuruznyak.bettingcompany.entity.event.market;

import com.kukuruznyak.bettingcompany.entity.enums.OutcomeNames;
import com.kukuruznyak.bettingcompany.entity.event.Participant;

public abstract class MarketItem {
    protected Participant participant;
    protected Outcome[] outcomes;

    public abstract void updateCoefficient(OutcomeNames outcomeName, double newCoefficient);
}
