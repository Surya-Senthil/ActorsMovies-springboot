package com.hollywood.cinema.Actors;

import com.hollywood.cinema.HollywoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String displayAddNewActor (Model model) {
        model.addAttribute("title", "Add Actor");
        model.addAttribute("actor", new Actors());
        return "actors/addActor";
    }

    @PostMapping(path="/add")
    public String processAddNewActor (@ModelAttribute Actors actor) {
        hollywoodService.addNewActor(actor);
        return "redirect:";
    }

    @GetMapping(path="")
    public String getAllActors(Model model) {
        model.addAttribute("actors", hollywoodService.getAllActors());

        model.addAttribute("title", "Hollywood Actors");
        return "actors/actors";
    }

    @GetMapping(path="/update")
    public String displayUpdateActor (Model model) {
        model.addAttribute("title", "Update Actor");
        model.addAttribute("actor", new Actors());
        model.addAttribute("actors", hollywoodService.getAllActors());
        return "actors/updateActor";
    }
    
    @PostMapping(path="/update")
    public String processUpdateActors (@ModelAttribute Actors actor) {
        hollywoodService.updateActor(actor);
        return "redirect:";
    }

    @GetMapping(path="/delete")
    public String displayDeleteActor(Model model){
        model.addAttribute("title", "Remove Actor");
        model.addAttribute("actors", hollywoodService.getAllActors());
        return "actors/deleteActor";
    }
    
    @PostMapping(path="/delete")
    public String processDeleteActor(@RequestParam int[] ids){
        for(int id : ids)
            hollywoodService.deleteActor(id);
        return "redirect:";
    }
}

