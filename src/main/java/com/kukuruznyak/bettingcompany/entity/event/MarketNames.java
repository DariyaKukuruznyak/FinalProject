package com.kukuruznyak.bettingcompany.entity.event;

public enum MarketNames {
    WINNER{
        @Override
        public String toString() {
            return "Winner";
        }
    },
    WIN_LOSE{
        @Override
        public String toString() {
            return "To Win / Not To Win";
        }
    }
}
