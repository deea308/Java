package filter;

import domain.Entity;
import domain.Orders;

public class FilterOrderByCakeFlavor implements AbstractFilter {
    private String flavorToBeFilteredBy;
    public FilterOrderByCakeFlavor(String newCakeFlavor) {this.flavorToBeFilteredBy = newCakeFlavor;}

    @Override
    public boolean accept(Entity entity) {
        if(entity instanceof Orders) {
            Orders orders = (Orders) entity;
            return orders.getCakeOrderFlavor().equals(flavorToBeFilteredBy);
        }
        return false;
    }

}
