package test;

import domain.Cake;
import repository.CakeRepository;
import repository.FakeRepository;
import repository.IRepository;
import service.CakeService;

import java.util.List;

public class ServiceTest {
    public void testAll_correct_valid(){
        CakeRepository fakeRepo= new CakeRepository();
        CakeService cakeService= new CakeService(fakeRepo);

        Iterable<Cake> cakes = cakeService.getAllCakes();
        int size=0;
        for(Cake currentCake: cakes)
            size++;

        assert size==2;
    }
}
