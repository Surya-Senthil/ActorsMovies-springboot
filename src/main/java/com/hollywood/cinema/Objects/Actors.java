package com.hollywood.cinema.Objects;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Actors {
    @Id
    @GeneratedValue
    private int actorID;
    private String name;
    private int age;

    @JsonIgnore
    @ManyToMany(mappedBy = "starred")
    public Set<Movies> actedIn = new HashSet<>();

    public void add(Movies movie){
        actedIn.add(movie);
    }

    public Actors() {
    }

    public Actors(int actorID, String name, int age) {
        this.actorID = actorID;
        this.name = name;
        this.age = age;
    }

    public int getActorID() {
        return this.actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Actors)) {
            return false;
        }
        Actors actors = (Actors) o;
        return actorID == actors.actorID && Objects.equals(name, actors.name) && age == actors.age;
    }

    @Override
    public String toString() {
        return "{" +
            " actorID='" + getActorID() + "'" +
            ", name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }

}
