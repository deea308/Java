package domain;

import java.io.Serializable;
import java.util.Objects;

public class Cake extends Entity<Integer> implements Serializable {
    private String flavor;
    private int price;
    private int kg;

    public Cake(int id,String flavor, int price, int kg) {
        super(id);
        this.flavor = flavor;
        this.price = price;
        this.kg = kg;
    }

    public String getFlavor() {return flavor;}
    public void setFlavor(String flavor) {this.flavor = flavor;}

    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}

    public int getKg() {return kg;}
    public void setKg(int kg) {this.kg = kg;}

    @Override
    public boolean equals(Object anotherCake) {
        if (this == anotherCake) return true;
        if (anotherCake == null || getClass() != anotherCake.getClass()) return false;
        Cake cake = (Cake) anotherCake;
        return price == cake.price && kg == cake.kg && Objects.equals(flavor, cake.flavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flavor, price, kg);
    }

    @Override
    public String toString() {
        return "Cake{" +" id="+ id+
                ", flavor='" + flavor + '\'' +
                ", price=" + price +
                ", kg=" + kg +
                '}';
    }
}
