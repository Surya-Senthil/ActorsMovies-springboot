package com.hollywood.cinema;

import java.util.List;

import com.hollywood.cinema.Actors.Actors;
import com.hollywood.cinema.Actors.ActorsRepository;
import com.hollywood.cinema.Movies.Movies;
import com.hollywood.cinema.Movies.MoviesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private ActorsRepository actorsRepository;
    private MoviesRepository moviesRepository;

    @Autowired
    public MainController(ActorsRepository actorsRepository, MoviesRepository moviesRepository){
        this.actorsRepository = actorsRepository;
        this.moviesRepository = moviesRepository;
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
