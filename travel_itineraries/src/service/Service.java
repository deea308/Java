package service;

import domain.Travel;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }
    public void modify( int id,String name, String continent, String description, List<String> places) {
        Travel travel = new Travel(id,name,continent,description,places);
        repo.modify(travel.getId(),travel);
    }
    public Travel findById(Integer key){
        return repo.findById(key);
    }

    public Iterable<Travel> getAll(){
        return repo.getAll();
    }
    public void updateRecipe(Travel travel){
        repo.modify(travel.getId(),travel);
        repo.updateInDatabase(travel);
    }
}