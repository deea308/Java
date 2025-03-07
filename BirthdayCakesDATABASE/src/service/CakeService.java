package service;

import filter.AbstractFilter;
import repository.CakeRepository;
import domain.Cake;
import repository.RepositoryException;

import java.util.*;

public class CakeService {
    private CakeRepository cakeRepository;

    public CakeService(CakeRepository cakeRepository) {this.cakeRepository = cakeRepository;  }

    public void addCake(Cake cake )throws RepositoryException {
        cakeRepository.add(cake);
    }

    public void updateCake( Cake cake) throws RepositoryException {
        cakeRepository.update(cake.getId(), cake);
    }


    public void deleteCake(int id) throws RepositoryException {
        cakeRepository.delete(id);
    }

    public Optional<Cake> getCakeById(int id) throws RepositoryException {
        return Optional.ofNullable(cakeRepository.findByID(id)); // Return cake wrapped in Optional
    }
    public List<Cake> getAllCakes() {
        return new ArrayList<>((Collection) cakeRepository.getAll()); // Return all cakes as a list
    }


    public List<Cake> filterCakes(AbstractFilter filter) throws RepositoryException {
        List<Cake> filteredCakes= new ArrayList<>();
        for(Cake currentCake : cakeRepository.getAll()) {
            if(filter.accept(currentCake)) {
                filteredCakes.add(currentCake);
            }
        }
        if(filteredCakes.isEmpty()) {
            throw new RepositoryException("No cakes found");
        }
        return filteredCakes;
    }

    //STREAMS
    public List<Cake> allCakesWithTheSameFlavor_ascendingByPrice(String flavor) {
        List<Cake> cakes= new ArrayList<>();
        for(Cake currentCake : cakeRepository.getAll()) {
            cakes.add(currentCake);
        }

        List<Cake> filteredCakes= cakes.stream()
                .filter(cake->cake.getFlavor().equals(flavor))
                .sorted(Comparator.comparing(Cake::getPrice))
                .toList();
        return filteredCakes;
    }

    public List<Cake> allCakesWithTheSameKg_descendingByFlavor(int kg)  {
        List<Cake> cakes= new ArrayList<>();
        for(Cake currentCake : cakeRepository.getAll()) {
            cakes.add(currentCake);
        }

        List<Cake> filteredCakes= cakes.stream()
                .filter(cake->cake.getKg()==kg)
        .sorted(Comparator.comparing(Cake::getFlavor).reversed())
                .toList();
        return filteredCakes;
    }

    public List<Cake> allCakesWithTheSamePrice_ascendingByKg(int price){
        List<Cake> cakes= new ArrayList<>();
        for(Cake currentCake : cakeRepository.getAll()) {
            cakes.add(currentCake);
        }
        List<Cake> filteredCakes= cakes.stream()
                .filter(cake->cake.getPrice()==price)
                .sorted(Comparator.comparingDouble(Cake::getKg))
                .toList();
        return filteredCakes;

    }

    public List<Cake> allCakesWithTheSameFlavor_ascendingByKg(String flavor){
        List<Cake> cakes= new ArrayList<>();
        for(Cake currentCake : cakeRepository.getAll()) {
            cakes.add(currentCake);
        }
        List<Cake> filteredCakes= cakes.stream()
                .filter(cake->cake.getFlavor().equals(flavor))
                .sorted(Comparator.comparingDouble(Cake::getKg))
                .toList();
        return filteredCakes;
    }

    public List<Cake> allCakesWithTheSamePrice_acendingByFlavor(int price){
        List<Cake> cakes= new ArrayList<>();
        for(Cake currentCake : cakeRepository.getAll()) {
            cakes.add(currentCake);
        }
        List<Cake> filteredCakes= cakes.stream()
                .filter(cake->cake.getPrice()==price)
                .sorted(Comparator.comparing(Cake::getFlavor))
                .toList();
        return filteredCakes;
    }


}
