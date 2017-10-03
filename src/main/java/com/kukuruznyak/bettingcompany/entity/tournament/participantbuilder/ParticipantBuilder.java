package com.kukuruznyak.bettingcompany.entity.tournament.participantbuilder;

import com.kukuruznyak.bettingcompany.entity.tournament.Participant;

public class ParticipantBuilder {
    private Long id = null;
    private String name;
    private int age;
    private int weight;
    private String trainer;
    private String jockey;

    public ParticipantBuilder buildId(long id) {
        this.id = id;
        return this;
    }

    public ParticipantBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public ParticipantBuilder buildAge(int age) {
        this.age = age;
        return this;
    }

    public ParticipantBuilder buildWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public ParticipantBuilder buildTrainer(String trainer) {
        this.trainer = trainer;
        return this;
    }

    public ParticipantBuilder buildJockey(String jockey) {
        this.jockey = jockey;
        return this;
    }

    public Participant build() {
        Participant participant = new Participant();
        participant.setId(this.id);
        participant.setName(this.name);
        participant.setAge(this.age);
        participant.setWeight(this.weight);
        participant.setTrainer(this.trainer);
        participant.setJockey(this.jockey);
        ;
        return participant;
    }


}
