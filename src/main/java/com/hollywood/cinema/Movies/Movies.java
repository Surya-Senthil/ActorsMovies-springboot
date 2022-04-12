package com.hollywood.cinema.Movies;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.hollywood.cinema.Actors.Actors;

@Entity
public class Movies {
    @Id
    @GeneratedValue
    private int movieID;
    private String title;
    private int year;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "acted_in",
        joinColumns = {@JoinColumn(name = "actor_id")},
        inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    public Set<Actors> starred = new HashSet<>();

    public void add(Actors actor){
        starred.add(actor);
    }

    public Movies() {
    }

    public Movies(int movieID, String title, int year) {
        this.movieID = movieID;
        this.title = title;
        this.year = year;
    }

    public int getMovieID() {
        return this.movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "{" +
            " movieID='" + getMovieID() + "'" +
            ", title='" + getTitle() + "'" +
            ", year='" + getYear() + "'" +
            "}";
    }

}
