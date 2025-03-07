package repository;

import domain.Cake;
import domain.Entity;

import java.util.ArrayList;

public class FakeRepository<ID, T extends Entity<ID>> extends MemoryRepository<ID, T> implements IRepository<ID, T> {

    public Iterable<T> getAll(){
        ArrayList<Cake> fixedCakeCollection = new ArrayList<>();
        Cake c1= new Cake(34,"vanilla",32,2);
        Cake c2= new Cake(33,"salted caramel",30,2);
        fixedCakeCollection.add(c1);
        fixedCakeCollection.add(c2);
        return (Iterable<T>) fixedCakeCollection;
    }
}
