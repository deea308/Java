package service;

import domain.Cake;
import domain.Orders;
import filter.AbstractFilter;
import repository.OrderRepository;
import repository.RepositoryException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ServiceOrders {
    private OrderRepository orderRepository;

    public ServiceOrders(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    public void addOrder(Orders order) throws RepositoryException {
        orderRepository.add(order);
    }

    public void deleteOrder(int id) throws RepositoryException {
        orderRepository.delete(id);
    }

    public void updateOrder(Orders order) throws RepositoryException {
        orderRepository.update(order.getId(), order);
    }

    public Optional<Orders> getOrderById(int id) throws RepositoryException {
        orderRepository.findByID(id);
        return Optional.empty();
    }

    public List<Orders> getAllOrders()  {orderRepository.printAll();
        return List.of();}



    public void printOrders(){orderRepository.printAll();}

    public List<Orders> filterOrders(AbstractFilter filter) throws RepositoryException {
        List<Orders> filteredOrders = new ArrayList<>();
        for(Orders currentOrder: orderRepository.getAll()){
            if(filter.accept(currentOrder)){
                filteredOrders.add(currentOrder);
            }
        }
        if(filteredOrders.isEmpty()){
            System.out.println("No orders found");
        }
        return filteredOrders;
    }

    //stream
    public List<Orders> allOrdersWithTheSameCustomerName_decresingByFlavor(String name){
        List<Orders>  orders=new ArrayList<>();
        for(Orders currentOrder: orderRepository.getAll()){
            orders.add(currentOrder);
        }

        List<Orders> filteredOrders=orders.stream()
                .filter(order->order.getCustomerName().equals(name))
                .sorted(Comparator.comparing(Orders::getCakeOrderFlavor))
                .toList();
        return filteredOrders;
    }


}
