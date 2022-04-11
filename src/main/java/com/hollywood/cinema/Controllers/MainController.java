package com.hollywood.cinema.Controllers;

import java.util.List;

import com.hollywood.cinema.Objects.Actors;
import com.hollywood.cinema.Objects.Movies;
import com.hollywood.cinema.Repos.ActorsRepository;
import com.hollywood.cinema.Repos.MoviesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private ActorsRepository actorsRepository;
    @Autowired
    private MoviesRepository moviesRepository;

    @PostMapping(path="/addActor")
    public @ResponseBody Actors addNewActor (@ModelAttribute Actors actor) {
        return actorsRepository.save(actor);
    }

    @PostMapping(path="/addMovie")
    public @ResponseBody Movies addNewMovie (@ModelAttribute Movies movie) {
        return moviesRepository.save(movie);
    }

    @GetMapping(path="/allActors")
    public @ResponseBody List<Actors> getAllActors() {
        return actorsRepository.findAll();
    }

    @GetMapping(path="/allMovies")
    public @ResponseBody List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }

    @PutMapping(path="/addRelation")
    public @ResponseBody Movies addNewRelation (@RequestParam int movieId, @RequestParam int actorId) {
        Actors actor = actorsRepository.findById(actorId).get();
        Movies movie = moviesRepository.findById(movieId).get();
        movie.add(actor);
        return moviesRepository.save(movie);
    }

    @GetMapping(path="/allRelations")
    public @ResponseBody List<Movies> getAllRelations() {
        return moviesRepository.findAll();
    }
}
