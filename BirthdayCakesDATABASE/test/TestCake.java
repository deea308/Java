import domain.Cake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCake {
    private Cake cake;
    private Cake otherCake;

    @BeforeEach
    public void setUp_NewCakeObject_forTesting_valid() {
        cake = new Cake(1,"vanilla", 34,3);
        otherCake = new Cake(1,"vanilla",34,3);
    }
    @Test
    public void testGetFlavor_correctRepresentation(){

        Assertions.assertEquals("vanilla", cake.getFlavor());
    }

    @Test
    public void testGetPrice_correctRepresentation() {

        Assertions.assertEquals(34, cake.getPrice());
    }

    @Test
    public void testGetKg_correctRepresentation() {

        Assertions.assertEquals(3, cake.getKg());
    }

    @Test
    public void testGetId_correctRepresentation() {

        Assertions.assertEquals(1, cake.getId());
    }

    @Test
    public void testSetId_IdParameter_valid() {

        Assertions.assertDoesNotThrow(()->cake.setId(20));
    }

    @Test
    public void testSetFlavor_IdParameter_valid() {
        Assertions.assertDoesNotThrow(()->cake.setFlavor("chocolate"));
    }

    @Test
    public void testSetPrice_IdParameter_valid() {
        Assertions.assertDoesNotThrow(()->cake.setPrice(25));
    }

    @Test
    public void testSetKg_IdParameter_valid() {
        Assertions.assertDoesNotThrow(()->cake.setKg(1));
    }

    @Test
    public void testToStringRepresentation_forCakes_valid(){
       Assertions.assertEquals("Cake{ "+"id=1, "+"flavor='vanilla"  + '\'' + ", price=34"  + ", kg=3"  + '}', cake.toString());
    }

    @Test
    public void testEquals_2Cakes_valid(){
        Assertions.assertEquals(cake, otherCake);
    }

    @Test
    public void testHshCode_theSameHashCode_valid() {
        Assertions.assertEquals(cake.hashCode(), otherCake.hashCode());
    }

}
