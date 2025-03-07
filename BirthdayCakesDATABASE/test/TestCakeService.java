import domain.Cake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CakeRepository;
import repository.RepositoryException;
import service.CakeService;

import java.util.Optional;

public class TestCakeService {
    private CakeService cakeService;

    @BeforeEach
    void setUpCakeService_initializeService_valid(){
        cakeService = new CakeService(new CakeRepository());
        try{
            cakeService.addCake(new Cake(8,"choco",22,2));
            cakeService.addCake(new Cake(9,"caramel",22,2));
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddCake_byIdIFDoesntExist_valid(){
        try{
            cakeService.addCake(new Cake(1,"vanilla", 34, 3));
            Iterable<Cake> cakes = cakeService.getAllCakes();

            int size=0;
            for(Cake cake: cakes){
                size++;
            }
            Assertions.assertEquals(size, 3);
        }catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddCake_byIdIFExists_throwException(){
        try{
            cakeService.addCake(new Cake(8,"vanilla",22, 1));
        }catch (Exception e){
            Assertions.assertEquals(e.getMessage(), "Object with id 8 already exists");
        }
    }

    @Test
    public void testRemoveCake_byIdIFExists_valid(){
        try{
            cakeService.deleteCake(8);
            Iterable<Cake> cakes = cakeService.getAllCakes();
            int size=0;
            for(Cake cake: cakes){
                size++;
            }
            Assertions.assertEquals(size, 1);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRemoveCake_byIdIFDoesntExist_throwException(){
        try{
            cakeService.deleteCake(1);
        }catch (RepositoryException e) {
            Assertions.assertEquals(e.getMessage(), "Object with id 1 does not exist");
        }
    }

    @Test
    public void testUpdateCake_byIdIFExists_valid(){
        try{
            cakeService.updateCake(new Cake(8,"vanilla", 12,2));
            Iterable<Cake> cakes = cakeService.getAllCakes();
            Assertions.assertEquals(cakes.toString(), "[Cake{ id=8, flavor='vanilla', price=12, kg=2}, Cake{ id=9, flavor='caramel', price=22, kg=2}]");
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateCake_byIdIFDoesntExists_throwException(){
        try{
            cakeService.updateCake(new Cake(1,"Vanilla",22,2));
        } catch (RepositoryException e) {
            Assertions.assertEquals(e.getMessage(), "Object with id 1 does not exist");
        }
    }
    @Test
    public void testFindCake_byIdIfExist_valid() {
        try {
            Optional<Cake> cake = cakeService.getCakeById(8);
            Assertions.assertTrue(cake.isPresent()); // Ensure the Optional contains a value
            Assertions.assertEquals(new Cake(8, "choco", 22, 2), cake.get()); // Check the value
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testFindCake_byIdIfExist_throwException(){
        try{
            cakeService.getCakeById(1);
        } catch (RepositoryException e) {
            Assertions.assertEquals(e.getMessage(), "Object with id 1 does not exist");
        }
    }


}
