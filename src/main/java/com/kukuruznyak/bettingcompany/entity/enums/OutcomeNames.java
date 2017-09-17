package com.kukuruznyak.bettingcompany.entity.enums;

public enum OutcomeNames {
    WIN{
        @Override
        public String toString() {
            return "Win";
        }
    },LOSE{
        @Override
        public String toString() {
            return "Lose";
        }
    }
}
