package com.hollywood.cinema.Actors;

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
@RequestMapping(path = "/actors")
public class ActorsController {
    private HollywoodService hollywoodService;

    @Autowired
    public ActorsController(HollywoodService hollywoodService){
        this.hollywoodService = hollywoodService;
    }

    @PostMapping(path="/add")
    public Actors addNewActor (@RequestBody Actors actor) {
        return hollywoodService.addNewActor(actor);
    }

    @GetMapping(path="/all")
    public List<Actors> getAllActors() {
        return hollywoodService.getAllActors();
    }

    @PutMapping(path="/update")
    public void updateActors (@RequestBody ObjectNode objectNode) {
        hollywoodService.updateActor(objectNode.get("actorID").asInt(), new Actors(objectNode.get("name").asText(), objectNode.get("age").asInt()));
    }

    @DeleteMapping(path="/delete")
    public void deleteActor(@RequestBody int id){
        hollywoodService.deleteActor(id);
    }
}

