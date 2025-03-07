import domain.Cake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CakeRepository;
import repository.RepositoryException;

public class TestCakeRepo {
    private static CakeRepository cakeRepo;

    @BeforeEach
    void setUp_NewCakeRepository_forTesting_valid() {
        cakeRepo= new CakeRepository();
        try{
            cakeRepo.add(new Cake(8,"choco",22,2));
            cakeRepo.add(new Cake(9,"caramel",22,2));
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testAddCake_byIdIfDoesntExist_valid(){
        try{
            cakeRepo.add(new Cake(3,"choco",22,2));
            Iterable<Cake> cakes= cakeRepo.getAll();
            int size=0;
            for(Cake cake: cakes){
                size++;
            }
            Assertions.assertEquals(size, 3);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddCake_byIdIfExists_catchException(){
        try{
            cakeRepo.add(new Cake(8,"choco",22,2));
        } catch (RepositoryException e) {
            Assertions.assertEquals(e.getMessage(),"Object with id 8 already exists");
        }
    }

    @Test
    public void testRemoveCake_byIdIfExist_valid(){
        try{
            cakeRepo.delete(8);
            Iterable<Cake> cakes= cakeRepo.getAll();
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
    public void testRemoveCake_byIdIfDoesntExists_catchException(){
        try{
            cakeRepo.delete(1);
        } catch (RepositoryException e) {
            Assertions.assertEquals(e.getMessage(),"Object with id 1 does not exist");
        }
    }

    @Test
    public void testUpdateCake_byIdIfExists_valid(){
        try{
            cakeRepo.update(8,new Cake(8,"vanilla",34,3));
            Iterable<Cake> cakes= cakeRepo.getAll();
            Assertions.assertEquals(cakes.toString(),"[Cake{ id=8, flavor='vanilla', price=34, kg=3}, Cake{ id=9, flavor='caramel', price=22, kg=2}]");
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateCake_byIdIfDoesntExist_catchException()  {
        try{
            cakeRepo.update(1,new Cake(8,"vanilla",34,3));
        }catch (RepositoryException e) {
            Assertions.assertEquals(e.getMessage(),"Object with id 1 does not exist");
        }
    }

    @Test
    public void testFindCake_byIdIfExist_valid(){
        try{
            Assertions.assertEquals(cakeRepo.findByID(8),new Cake(8,"choco",22,2));
        }catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testFindCake_byIdIfDoesntExist_catchException(){
        try{
            cakeRepo.findByID(10);
        } catch (RepositoryException e) {
            Assertions.assertEquals(e.getMessage(),"Object with id 10 does not exist");
        }
    }

    @Test
    public void testPrintAllCakes_valid(){
        Assertions.assertEquals(cakeRepo.printAll(), 0);
    }
}
