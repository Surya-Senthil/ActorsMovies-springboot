package com.hollywood.cinema.Movies;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.hollywood.cinema.Actors.Actors;

@Entity
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieID;
    private String title;
    private int year;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "acted_in",
        joinColumns = {@JoinColumn(name = "actor_id")},
        inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    public Set<Actors> starred = new HashSet<>();

    public Movies() {
    }

    public Movies(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getStarred(){
        String ans = "";
        for(Actors actor : starred)
            ans += actor.getName() + ", ";
        return ans.substring(0, ans.length()-2);
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
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Movies)) {
            return false;
        }
        Movies movies = (Movies) o;
        return movieID == movies.movieID && Objects.equals(title, movies.title) && year == movies.year && Objects.equals(starred, movies.starred);
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
