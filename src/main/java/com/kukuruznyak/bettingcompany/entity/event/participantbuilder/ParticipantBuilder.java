package com.kukuruznyak.bettingcompany.entity.event.participantbuilder;

import com.kukuruznyak.bettingcompany.entity.event.Participant;

public class ParticipantBuilder {
    private String name;
    private int age;
    private int weight;
    private String trainer;
    private String jockey;

    ParticipantBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    ParticipantBuilder buildAge(int age) {
        this.age = age;
        return this;
    }

    ParticipantBuilder buildWeight(int weight) {
        this.weight = weight;
        return this;
    }

    ParticipantBuilder buildTrainer(String trainer) {
        this.trainer = trainer;
        return this;
    }

    ParticipantBuilder buildJockey(String jockey) {
        this.jockey = jockey;
        return this;
    }

    Participant build() {
        Participant participant = new Participant();
        participant.setName(this.name);
        participant.setAge(this.age);
        participant.setWeight(this.weight);
        participant.setTrainer(this.trainer);
        participant.setJockey(this.jockey);
        return participant;
    }
}
