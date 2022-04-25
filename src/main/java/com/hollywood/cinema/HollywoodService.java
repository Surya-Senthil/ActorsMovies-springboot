package com.hollywood.cinema;

import java.util.List;

import com.hollywood.cinema.Actors.Actors;
import com.hollywood.cinema.Actors.ActorsRepository;
import com.hollywood.cinema.Movies.Movies;
import com.hollywood.cinema.Movies.MoviesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HollywoodService {
    private MoviesRepository moviesRepository;
    private ActorsRepository actorsRepository;

    @Autowired
    public HollywoodService(MoviesRepository moviesRepository, ActorsRepository actorsRepository) {
        this.moviesRepository = moviesRepository;
        this.actorsRepository = actorsRepository;
    }

    public void addCastMembers(int actorID, int movieID) {
        Movies movie = moviesRepository.findById(movieID).orElse(null);
        Actors actor = actorsRepository.findById(actorID).orElse(null);
        movie.starred.add(actor);
        actor.actedIn.add(movie);
        moviesRepository.save(movie);
        actorsRepository.save(actor);
    }

    public Movies addNewMovie(Movies actor) {
        return moviesRepository.save(actor);
    }

    public List<Movies> getAllMovies(){
        return moviesRepository.findAll();
    }

    public void updateMovie(Movies movie) {
        Movies oldMovie = moviesRepository.findById(movie.getMovieID()).orElse(null);
        System.out.println(movie.toString());
        oldMovie.setYear(movie.getYear());
        oldMovie.setTitle(movie.getTitle());
        moviesRepository.save(oldMovie);
    }

    public void deleteMovie(int id){
        Movies movie = moviesRepository.findById(id).orElse(null);
        for(Actors actor : movie.starred)
            actor.actedIn.remove(movie);    
        moviesRepository.deleteById(id);
    }

    public Actors addNewActor(Actors actor) {
        return actorsRepository.save(actor);
    }

    public List<Actors> getAllActors(){
        return actorsRepository.findAll();
    }

    public void updateActor(Actors actor) {
        Actors oldActor = actorsRepository.findById(actor.getActorID()).orElse(null);
        oldActor.setAge(actor.getAge());
        oldActor.setName(actor.getName());
        actorsRepository.save(oldActor);
    }

    public void deleteActor(int id){
        Actors actor = actorsRepository.findById(id).orElse(null);
        for(Movies movie : actor.actedIn)
            movie.starred.remove(actor);
        actorsRepository.deleteById(id);
    }
}
