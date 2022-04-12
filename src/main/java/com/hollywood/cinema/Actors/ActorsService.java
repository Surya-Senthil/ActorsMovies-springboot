package com.hollywood.cinema.Actors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorsService {
    private ActorsRepository actorsRepository;

    @Autowired
    public ActorsService(ActorsRepository actorsRepository) {
        this.actorsRepository = actorsRepository;
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

}
