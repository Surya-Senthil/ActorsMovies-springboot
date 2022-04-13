package com.hollywood.cinema.Movies;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hollywood.cinema.HollywoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/movies")
public class MoviesController {
    private HollywoodService hollywoodService;

    @Autowired
    public MoviesController(HollywoodService hollywoodService){
        this.hollywoodService = hollywoodService;
    }

    @PostMapping(path="/add")
    public Movies addNewMovie (@RequestBody Movies movie) {
        return hollywoodService.addNewMovie(movie);
    }

    @GetMapping(path="/all")
    public List<Movies> getAllMovies() {
        return hollywoodService.getAllMovies();
    }

    @PutMapping(path="/update")
    public Movies updateActors (@RequestBody ObjectNode objectNode) {
        return hollywoodService.updateMovie(objectNode.get("actorID").asInt(), new Movies(objectNode.get("name").asText(), objectNode.get("age").asInt()));
    }

    @PostMapping(path="/addCast")
    public void addCastMembers (@RequestBody ObjectNode objectNode) {
        hollywoodService.addCastMembers(objectNode.get("actorID").asInt(), objectNode.get("movieID").asInt());
    }

    @DeleteMapping(path="/delete")
    public void deleteActor(@RequestBody int id){
        hollywoodService.deleteMovie(id);
    }
}
