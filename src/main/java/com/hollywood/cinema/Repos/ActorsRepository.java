package com.hollywood.cinema.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hollywood.cinema.Objects.Actors;

public interface ActorsRepository extends JpaRepository<Actors, Integer> {

}