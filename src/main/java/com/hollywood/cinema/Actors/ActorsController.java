package com.hollywood.cinema.Actors;

import com.hollywood.cinema.HollywoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/actors")
public class ActorsController {
    private HollywoodService hollywoodService;

    @Autowired
    public ActorsController(HollywoodService hollywoodService){
        this.hollywoodService = hollywoodService;
    }

    @GetMapping(path="/add")
    public String displayAddNewActor (Model model) {
        model.addAttribute("title", "Add Actor");
        return "actors/addActor";
    }

    // @PostMapping(path="/add")
    // public String processAddNewActor (@RequestBody Actors actor) {
    //     hollywoodService.addNewActor(actor);
    //    return "redirect:";
    // }

    @PostMapping(path="/add")
    public String processAddNewActor (@RequestParam String name, @RequestParam int age) {
        hollywoodService.addNewActor(new Actors(name, age));
       return "redirect:";
    }

    @GetMapping(path="")
    public String getAllActors(Model model) {
        model.addAttribute("actors", hollywoodService.getAllActors());

        model.addAttribute("title", "Hollywood Actors");
        return "actors/actors";
    }

    @PutMapping(path="/update")
    public void updateActors (@RequestBody Actors actor) {
        hollywoodService.updateActor(actor);
    }

    @DeleteMapping(path="/delete")
    public void deleteActor(@RequestBody int id){
        hollywoodService.deleteActor(id);
    }
}

