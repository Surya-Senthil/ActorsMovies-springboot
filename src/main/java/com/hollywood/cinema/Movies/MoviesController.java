package com.hollywood.cinema.Movies;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hollywood.cinema.HollywoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/movies")
public class MoviesController {
    private HollywoodService hollywoodService;

    @Autowired
    public MoviesController(HollywoodService hollywoodService){
        this.hollywoodService = hollywoodService;
    }

    @GetMapping(path="/add")
    public String displayAddNewMovie (Model model) {
        model.addAttribute("title", "Add Movie");
        model.addAttribute("movie", new Movies());
        return "movies/addMovie";
    }

    @PostMapping(path="/add")
    public String processAddNewMovieForm (@ModelAttribute Movies movie) {
        hollywoodService.addNewMovie(movie);
        return "redirect:";
    }

    @GetMapping(path="")
    public String getAllMovies(Model model) {
        model.addAttribute("movies", hollywoodService.getAllMovies());

        model.addAttribute("title", "Hollywood Movies");
        return "movies/movies";
    }

    @GetMapping(path="/update")
    public String displayUpdateMovie (Model model) {
        model.addAttribute("title", "Update Movie");
        model.addAttribute("movie", new Movies());
        model.addAttribute("movies", hollywoodService.getAllMovies());
        return "movies/updateMovie";
    }
    
    @PostMapping(path="/update")
    public String processUpdateMovies (@ModelAttribute Movies movie) {
        hollywoodService.updateMovie(movie);
        return "redirect:";
    }

    @GetMapping(path="/delete")
    public String displayDeleteMovie(Model model){
        model.addAttribute("title", "Remove Movie");
        model.addAttribute("movies", hollywoodService.getAllMovies());
        return "movies/deleteMovie";
    }
    
    @PostMapping(path="/delete")
    public String processDeleteMovie(@RequestParam int[] ids){
        for(int id : ids)
            hollywoodService.deleteMovie(id);
        return "redirect:";
    }

    @PostMapping(path="/addCast")
    public void addCastMembers (@RequestBody ObjectNode objectNode) {
        hollywoodService.addCastMembers(objectNode.get("actorID").asInt(), objectNode.get("movieID").asInt());
    }
}
