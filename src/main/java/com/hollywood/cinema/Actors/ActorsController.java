package com.hollywood.cinema.Actors;

import com.hollywood.cinema.HollywoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String getAddNewActor (Model model) {
        model.addAttribute("title", "Add Actor");
        model.addAttribute("actor", new Actors());
        return "actors/addActor";
    }

    @PostMapping(path="/add")
    public String postAddNewActor (@ModelAttribute Actors actor) {
        hollywoodService.addNewActor(actor);
        return "redirect:";
    }

    @GetMapping(path="")
    public String getAllActors(Model model) {
        model.addAttribute("actors", hollywoodService.getAllActors());
        model.addAttribute("title", "Hollywood Actors");
        return "actors/actors";
    }

    @GetMapping(path="/update/{id}")
    public String getUpdateActor (@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Update Actor");
        model.addAttribute("actor", hollywoodService.getActor(id));
        return "actors/updateActor";
    }
    
    @PostMapping(path="/update/{id}")
    public String postUpdateActors (@PathVariable("id") int id, @ModelAttribute Actors actor) {
        hollywoodService.updateActor(id, actor);
        return "redirect:/actors";
    }
    
    @PostMapping(path="/delete/{id}")
    public String postDeleteActor(@PathVariable("id") int id){
        hollywoodService.deleteActor(id);
        return "redirect:/actors";
    }

    @GetMapping(path="/addMovietoActor/{id}")
    public String getAddMovieToActor (@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Update Actor");
        model.addAttribute("movies", hollywoodService.getAllMovies());
        return "actors/addMovietoActor";
    }
    
    @PostMapping(path="/addMovietoActor/{id}")
    public String postAddMovieToActor (@PathVariable("id") int actorID, @RequestParam int[] ids) {
        hollywoodService.addMovietoActor(actorID, ids);
        return "redirect:/actors";
    }
}

