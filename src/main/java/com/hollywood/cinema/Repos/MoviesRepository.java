package com.hollywood.cinema.Repos;

import com.hollywood.cinema.Objects.Movies;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {

}