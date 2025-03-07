package domain;


import java.io.Serializable;
import java.util.Objects;

public class Orders extends Entity<Integer> implements Serializable {
    private String customerName;
    private String cakeOrderFlavor;

    public Orders(Integer id, String customerName, String flavor) {
        super(id);
        this.customerName = customerName;
        this.cakeOrderFlavor = flavor;

    }

    @Override
    public boolean equals(Object anotherOrder) {
        if (this == anotherOrder) return true;
        if (anotherOrder == null || getClass() != anotherOrder.getClass()) return false;
        Orders orders = (Orders) anotherOrder;
        return Objects.equals(customerName, orders.customerName) && Objects.equals(cakeOrderFlavor, orders.cakeOrderFlavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, cakeOrderFlavor);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCakeOrderFlavor() {
        return cakeOrderFlavor;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCakeOrderFlavor(String cakeOrderFlavor) {
        this.cakeOrderFlavor = cakeOrderFlavor;
    }

    @Override
    public String toString() {
        return "Orders{" +" id="+id+
                ", customerName='" + customerName + '\'' +
                ", cakeOrderFlavor='" + cakeOrderFlavor + '\'' +
                '}';
    }

}

