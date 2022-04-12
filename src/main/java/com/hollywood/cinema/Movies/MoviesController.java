package com.hollywood.cinema.Movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/movies")
public class MoviesController {
    private MoviesRepository moviesRepository;

    @Autowired
    public MoviesController(MoviesRepository moviesRepository){
        this.moviesRepository = moviesRepository;
    }

    @PostMapping(path="/add")
    public Movies addNewMovie (@RequestBody Movies movie) {
        return moviesRepository.save(movie);
    }

    @GetMapping(path="/all")
    public List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }
}
