package com.hollywood.cinema.Actors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/actors")
public class ActorsController {
    private ActorsService actorsService;

    @Autowired
    public ActorsController(ActorsService actorsService){
        this.actorsService = actorsService;
    }

    @PostMapping(path="/add")
    public Actors addNewActor (@RequestBody Actors actor) {
        return actorsService.addNewActor(actor);
    }

    @GetMapping(path="/all")
    public List<Actors> getAllActors() {
        return actorsService.getAllActors();
    }

    @DeleteMapping(path="/delete")
    public void deleteActor(@RequestBody int id){
        actorsService.deleteActor(id);
    }
}

