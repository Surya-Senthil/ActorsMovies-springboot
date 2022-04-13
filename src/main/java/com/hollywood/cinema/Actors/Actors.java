package com.hollywood.cinema.Actors;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hollywood.cinema.Movies.Movies;

@Entity
public class Actors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorID;
    private String name;
    private int age;

    @JsonIgnore
    @ManyToMany(mappedBy = "starred", cascade = CascadeType.ALL)
    public Set<Movies> actedIn = new HashSet<>();

    public Actors() {
    }

    public Actors(String name, int age) {
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
