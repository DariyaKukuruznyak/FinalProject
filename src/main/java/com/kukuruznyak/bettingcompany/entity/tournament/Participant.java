package com.kukuruznyak.bettingcompany.entity.tournament;

import com.kukuruznyak.bettingcompany.entity.Model;

import java.util.LinkedList;
import java.util.List;

public class Participant extends Model {
    /**
     * unique
     */
    private String name;
    private int age;
    private int weight;
    private String trainer;
    private String jockey;
    private List<Tournament> tournaments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getJockey() {
        return jockey;
    }

    public void setJockey(String jockey) {
        this.jockey = jockey;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public void addTournament(Tournament tournament) {
        if (this.tournaments == null) {
            this.tournaments = new LinkedList<>();
        }
        this.tournaments.add(tournament);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;

        Participant that = (Participant) o;

        if (getAge() != that.getAge()) return false;
        if (getWeight() != that.getWeight()) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getTrainer().equals(that.getTrainer())) return false;
        return getJockey().equals(that.getJockey());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAge();
        result = 31 * result + getWeight();
        result = 31 * result + getTrainer().hashCode();
        result = 31 * result + getJockey().hashCode();
        return result;
    }

    public String getFullName() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", trainer='" + trainer + '\'' +
                ", jockey='" + jockey + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", trainer='" + trainer + '\'' +
                ", jockey='" + jockey + '\'' +
                ", tournaments=" + tournaments +
                '}';
    }
}
