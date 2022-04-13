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
        Movies temp = moviesRepository.findById(movieID).orElse(null);
        temp.starred.add(actorsRepository.findById(actorID).orElse(null));
        moviesRepository.save(temp);
    }

    public List<Movies> getAllMovies(){
        return moviesRepository.findAll();
    }

    public Movies addNewMovie(Movies actor) {
        return moviesRepository.save(actor);
    }

    public void deleteMovie(int id){
        moviesRepository.deleteById(id);
    }

    public Movies updateMovie(int id, Movies actor) {
        moviesRepository.deleteById(id);
        moviesRepository.save(actor);
        return actor;
    }

    public List<Actors> getAllActors(){
        return actorsRepository.findAll();
    }

    public Actors addNewActor(Actors actor) {
        return actorsRepository.save(actor);
    }

    public void deleteActor(int id){
        actorsRepository.deleteById(id);
    }

    public Actors updateActor(int id, Actors actor) {
        actorsRepository.deleteById(id);
        actorsRepository.save(actor);
        return actor;
    }
}
