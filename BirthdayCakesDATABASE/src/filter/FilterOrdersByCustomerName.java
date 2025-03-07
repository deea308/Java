package filter;

import domain.Entity;
import domain.Orders;

public class FilterOrdersByCustomerName implements AbstractFilter{

    private String customerName;
    public FilterOrdersByCustomerName(String customerName) {this.customerName = customerName;}

    @Override
    public boolean accept(Entity entity) {
        if(entity instanceof Orders){
            Orders orders = (Orders)entity;
            return orders.getCustomerName().equals(customerName);
        }
        return false;
    }
}
