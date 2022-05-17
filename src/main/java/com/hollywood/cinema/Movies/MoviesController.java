package com.hollywood.cinema.Movies;

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

    @GetMapping(path="/update/{id}")
    public String getUpdateMovie (@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Update Movie");
        model.addAttribute("movie", hollywoodService.getMovie(id));
        return "movies/updateMovie";
    }
    
    @PostMapping(path="/update/{id}")
    public String postUpdateMovie (@PathVariable("id") int id, @ModelAttribute Movies movie) {
        hollywoodService.updateMovie(id, movie);
        return "redirect:/movies";
    }

    @PostMapping(path="/delete/{id}")
    public String postDeleteMovie(@PathVariable("id") int id){
        hollywoodService.deleteMovie(id);
        return "redirect:/movies";
    }

    @GetMapping(path="/addActortoMovie/{id}")
    public String getAddActortoMovie (@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Update Movie");
        model.addAttribute("actors", hollywoodService.getAllActors());
        return "movies/addActortoMovie";
    }
    
    @PostMapping(path="/addActortoMovie/{id}")
    public String postAddActortoMovie (@PathVariable("id") int movieID, @RequestParam int[] ids) {
        hollywoodService.addActortoMovie(movieID, ids);
        return "redirect:/movies";
    }
}
