import UI.Ui;
import domain.Cake;
import domain.Orders;
import repository.CakeRepository;
import repository.OrderRepository;
import repository.RepositoryException;
import repository.data_bases.CakesDBRepository;
import service.CakeService;
import service.ServiceOrders;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws RepositoryException, IOException {


        CakeRepository cakeRepository = new CakeRepository();
        OrderRepository orderRepository = new OrderRepository();

        Cake cake1= new Cake(1,"vanilla",30,3);
        Cake cake2= new Cake(2,"lime",20,2);
        Cake cake3= new Cake(3,"chocolate",15,3);
        Cake cake4= new Cake(4,"salted caramel",30,3);
        Cake cake5= new Cake(5,"vanilla",10,1);


        cakeRepository.add(cake1);
        cakeRepository.add(cake2);
        cakeRepository.add(cake3);
        cakeRepository.add(cake4);
        cakeRepository.add(cake5);

        Orders order1= new Orders(1,"Andreea","vanilla");
        Orders order2= new Orders(2,"Ana","lime");
        Orders order3= new Orders(3,"Diana","salted caramel");
        Orders order4= new Orders(4,"Alessia","vanilla");
        Orders order5= new Orders(5,"Dragos","chocolate");

        orderRepository.add(order1);
        orderRepository.add(order2);
        orderRepository.add(order3);
        orderRepository.add(order4);
        orderRepository.add(order5);
        CakeService cakeService = new CakeService( cakeRepository);
        ServiceOrders ordersService = new ServiceOrders( orderRepository);
        Ui app = new Ui(cakeService, ordersService);
        app.ShowMenu();


    }


}
