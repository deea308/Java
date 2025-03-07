package filter;

import domain.Entity;
import domain.Cake;


public class FilterCakesByFlavor implements AbstractFilter {
    private String flavorToFilterBy;

    public FilterCakesByFlavor(String flavorToBeFiltered) {
        this.flavorToFilterBy = flavorToBeFiltered;
    }

    @Override
    public boolean accept(Entity entity) {
        if(entity instanceof Cake) {
            Cake cake = (Cake) entity;
            return cake.getFlavor().equals(flavorToFilterBy);
        }
        return false;
    }
}

