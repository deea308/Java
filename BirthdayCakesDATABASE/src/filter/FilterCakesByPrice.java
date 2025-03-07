package filter;

import domain.Cake;
import domain.Entity;

public class FilterCakesByPrice implements AbstractFilter {
    private int price;
    public FilterCakesByPrice(int price) {this.price = price;}

    @Override
    public boolean accept(Entity entity) {
        if(entity instanceof Cake) {
            Cake cake = (Cake) entity;
            return cake.getPrice() > price;
        }
        return false;
    }
}
