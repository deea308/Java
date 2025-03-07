package UI;

import domain.Cake;
import domain.Orders;
import filter.*;
import repository.CakeRepository;
import repository.OrderRepository;
import repository.RepositoryException;
import repository.data_bases.CakesDBRepository;
import repository.data_bases.OrdersDBRepository;
import repository.file.BinaryFileCakesRepository;
import repository.file.BinaryOrdersFileRepository;
import repository.file.CakeTextFileRepository;
import service.CakeService;
import service.ServiceOrders;
import test.ServiceTest;
//import test.ServiceTest;

import java.io.*;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

public class Ui {
    private CakeService cakeService;
    private ServiceOrders serviceOrders;
    private Scanner scanner;



    public Ui(CakeService cakeService, ServiceOrders serviceOrders) throws RepositoryException {
        if(cakeService==null){
    throw new IllegalStateException("cakeService cannot null");
        }
        this.cakeService = cakeService;
        this.serviceOrders = serviceOrders;
        this.scanner = new Scanner(System.in);

    }

    public static void testFakeRepo_goodTests_valid(){
        ServiceTest testFakeRepo= new ServiceTest();
        testFakeRepo.testAll_correct_valid();
        System.out.println("Fake repo alright");
    }

    public static int CAKE_CRUD_OPERATIONS=1;
    public static int ORDERS_CRUD_OPERATIONS=2;
    public static int EXIT_MENU_OBJECTS=3;

    public static int ADD_A_CAKE=1;
    public static int DELETE_A_CAKE=2;
    public static int UPDATE_A_CAKE=3;
    public static int DISPLAY_A_CAKE=4;
    public static int DISPLAY_ALL_CAKES=5;
    public static int FILTER_CAKE_BY_FLAVOR=6;
    public static int FILTER_CAKE_BY_PRICE=7;
    public static int ADD_A_CAKE_FILE_OR_DATABASE=8;
    public static int PRINT_CAKES_FROM_FILE_OR_DATABASE=9;
    public static int DELETE_CAKE_FILE=10;
    public static int UPDATE_CAKE_FILE=11;
    public static int HANDLE_CAKES_REPORT=12;
    public static int DELETE_CAKE_DATABASE=13;
    public static int UPDATE_CAKE_FROM_DATABASE=14;
    public static int EXIT_THE_CAKE_PROGRAM=0;

    public static int ADD_AN_ORDER=1;
    public static int DELETE_AN_ORDER=2;
    public static int UPDATE_AN_ORDER=3;
    public static int DISPLAY_AN_ORDER=4;
    public static int DISPLAY_ALL_ORDERS=5;
    public static int FILTER_ORDER_BY_CAKE_FLAVOR=6;
    public static int FILTER_ORDER_BY_CUSTOMER_NAME=7;
    public static int ADD_AN_ORDER_FILE=8;
    public static int PRINT_ORDERS_FROM_FILE=9;
    public static int DELETE_ORDER_FILE=10;
    public static int UPDATE_ORDER_FILE=11;
    public static int DELETE_ORDER_DATABASE=12;
    public static int UPDATE_ORDERS_FROM_DATABASE=13;
    public static int EXIT_THE_ORDER_PROGRAM=0;

    void printMenu(){
        System.out.println("Select an object: ");
        System.out.println(CAKE_CRUD_OPERATIONS+" Cakes");
        System.out.println(ORDERS_CRUD_OPERATIONS+" Orders");
        System.out.println(EXIT_MENU_OBJECTS+" Exit");
    }

    void printCakeMenu(){
        System.out.println("Select option: ");
        System.out.println(ADD_A_CAKE+" Add a cake order");
        System.out.println(DELETE_A_CAKE+" Delete a cake order");
        System.out.println(UPDATE_A_CAKE+" Update a cake order");
        System.out.println(DISPLAY_A_CAKE+" Display a cake ");
        System.out.println(DISPLAY_ALL_CAKES+" Display all orders");
        System.out.println(FILTER_CAKE_BY_FLAVOR+" Display cakes filtered by flavor");
        System.out.println(FILTER_CAKE_BY_PRICE+" Display cakes filtered by price");
        System.out.println(ADD_A_CAKE_FILE_OR_DATABASE+" Add a cake to file/database");
        System.out.println(PRINT_CAKES_FROM_FILE_OR_DATABASE+" Print from file/database");
        System.out.println(DELETE_CAKE_FILE+" Delete from file");
        System.out.println(UPDATE_CAKE_FILE+" Update from file");
        System.out.println(HANDLE_CAKES_REPORT+" Handle cakes report");
        System.out.println(DELETE_CAKE_DATABASE+" Delete from database");
        System.out.println(UPDATE_CAKE_FROM_DATABASE+" Update from database");
        System.out.println(EXIT_THE_CAKE_PROGRAM+" Exit");
        System.out.printf("Choose an option: ");
    }

    void printOrderMenu(){
        System.out.println("Select option:");
        System.out.println(ADD_AN_ORDER+" Add a cake order");
        System.out.println(DELETE_AN_ORDER+" Delete a cake order");
        System.out.println(UPDATE_AN_ORDER+" Update a cake order");
        System.out.println(DISPLAY_AN_ORDER+" Update a cake order");
        System.out.println(DISPLAY_ALL_ORDERS+" Display all orders");
        System.out.println(FILTER_ORDER_BY_CAKE_FLAVOR+" Display cakes orders filtered by flavor");
        System.out.println(FILTER_ORDER_BY_CUSTOMER_NAME+" Display cakes orders filtered by customer");
        System.out.println(ADD_AN_ORDER_FILE+" Add a order to file/database");
        System.out.println(PRINT_ORDERS_FROM_FILE+" Print from file/database");
        System.out.println(DELETE_ORDER_FILE+" Delete from file");
        System.out.println(UPDATE_ORDER_FILE+" Update from file");
        System.out.println(DELETE_ORDER_DATABASE+" Delete from database");
        System.out.println(UPDATE_ORDERS_FROM_DATABASE+" Update from database");
        System.out.println(EXIT_THE_ORDER_PROGRAM+" Exit");
    }

    public void ShowMenu() throws RepositoryException, IOException {
        while(true){
            printMenu();
            int choiceForTheMenu=scanner.nextInt();

            if(choiceForTheMenu==CAKE_CRUD_OPERATIONS){ CakeMenu(); }
            else if(choiceForTheMenu==ORDERS_CRUD_OPERATIONS){ OrdersMenu(); }
            else if(choiceForTheMenu==EXIT_MENU_OBJECTS){
                System.out.println("Exit the program");
                break;
            }
        }

    }

    private void CakeMenu() throws RepositoryException, IOException{
        while (true) {
            printCakeMenu();
            int choiceForTheCakeMenu = scanner.nextInt();

            if (choiceForTheCakeMenu == ADD_A_CAKE) addCake();
            else if (choiceForTheCakeMenu == DELETE_A_CAKE) deleteCake();
            else if (choiceForTheCakeMenu == UPDATE_A_CAKE) updateCake();
            else if (choiceForTheCakeMenu == DISPLAY_A_CAKE) printACake();
            else if (choiceForTheCakeMenu == DISPLAY_ALL_ORDERS) printAllCakes();
            else if (choiceForTheCakeMenu == FILTER_CAKE_BY_FLAVOR) filterCakesByFlavor();
            else if (choiceForTheCakeMenu == FILTER_CAKE_BY_PRICE) filterCakesByPrice();
            else if (choiceForTheCakeMenu == ADD_A_CAKE_FILE_OR_DATABASE) addCakeToFile();
            else if (choiceForTheCakeMenu == PRINT_CAKES_FROM_FILE_OR_DATABASE) printCakesFromFile();
            else if (choiceForTheCakeMenu == DELETE_CAKE_FILE) deleteCakeFromFile();
            else if (choiceForTheCakeMenu == UPDATE_CAKE_FILE) updateCakeInFile();
            else if(choiceForTheCakeMenu==HANDLE_CAKES_REPORT) handleCakes();
            else if(choiceForTheCakeMenu==DELETE_CAKE_DATABASE) deleteCakeFromDatabase();
            else if(choiceForTheCakeMenu==UPDATE_CAKE_FROM_DATABASE) updateCakeFromDatabase();
            else if (choiceForTheCakeMenu == EXIT_THE_CAKE_PROGRAM) System.exit(0);
            else System.out.println("Invalid choice. Try again. ");
        }
    }
        private void OrdersMenu () throws RepositoryException, IOException {
            while (true) {
                printOrderMenu();
                int choiceForTheOrderMenu = scanner.nextInt();

                if (choiceForTheOrderMenu == ADD_AN_ORDER) addOrder();
                else if (choiceForTheOrderMenu == DELETE_AN_ORDER) deleteOrder();
                else if (choiceForTheOrderMenu == UPDATE_A_CAKE) updateOrder();
                else if (choiceForTheOrderMenu == DISPLAY_A_CAKE) printAnOrder();
                else if (choiceForTheOrderMenu == DISPLAY_ALL_ORDERS) printAllOrders();
                else if (choiceForTheOrderMenu == FILTER_CAKE_BY_FLAVOR) filterOrdersByCakesFlavor();
                else if (choiceForTheOrderMenu == FILTER_CAKE_BY_PRICE) filterOrdersByCustomerName();
                else if (choiceForTheOrderMenu == ADD_AN_ORDER_FILE) addOrderToFile();
                else if (choiceForTheOrderMenu == PRINT_ORDERS_FROM_FILE) printOrdersFromFile();
                else if(choiceForTheOrderMenu == DELETE_ORDER_FILE) deleteOrderFromFile();
                else if(choiceForTheOrderMenu == UPDATE_ORDER_FILE) updateOrderInFile();
                else if(choiceForTheOrderMenu==DELETE_ORDER_DATABASE) deletOrderFromDatabase();
                else if(choiceForTheOrderMenu==UPDATE_ORDERS_FROM_DATABASE) updateOrdersFromDatabase();
                else if (choiceForTheOrderMenu == EXIT_THE_CAKE_PROGRAM) System.exit(0);
                else
                    System.out.println("Invalid choice. Try again. ");
            }
        }

        private void addCake () {
            System.out.println("Enter cake Id: ");
            int idOfTheCake = scanner.nextInt();
            System.out.println("Enter cake flavour: ");
            String flavourOfTheCake = scanner.next();
            System.out.println("Enter cake price: ");
            int priceOfTheCake = scanner.nextInt();
            System.out.println("Enter cake kg: ");
            int kgOfTheCake = scanner.nextInt();
            Cake newCake = new Cake(idOfTheCake, flavourOfTheCake, priceOfTheCake, kgOfTheCake);

            try {
                cakeService.addCake(newCake);
                System.out.println("Cake added");
                //printAllCakes();
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }

        private void addOrder () {
            System.out.println("Enter order Id: ");
            int idOfTheOrder = scanner.nextInt();
            System.out.println("Enter customer order's name: ");
            String customerNameOfTheOrder = scanner.next();

            System.out.println("Enter order flavour: ");
            String flavourOfTheOrder = scanner.next();
            Orders newOrder = new Orders(idOfTheOrder, customerNameOfTheOrder, flavourOfTheOrder);
            try {
                serviceOrders.addOrder(newOrder);
                System.out.println("Order added");
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }

        private void deleteCake () {
            System.out.println("Enter cake Id to delete: ");
            int idOfTheCake = scanner.nextInt();
            try {
                cakeService.deleteCake(idOfTheCake);
                System.out.println("Cake deleted");
                //printAllCakes();
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }

        private void deleteOrder () {
            System.out.println("Enter order Id to delete: ");
            int idOfTheOrder = scanner.nextInt();
            try {
                serviceOrders.deleteOrder(idOfTheOrder);
                System.out.println("Order deleted");
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }

        private void updateCake () {
            System.out.println("Enter cake Id to update: ");
            int idOfTheCake = scanner.nextInt();

            System.out.println("Enter new flavor: ");
            String newFlavor = scanner.next();
            System.out.println("Enter new price: ");
            int newPrice = scanner.nextInt();
            System.out.println("Enter new kg: ");
            int newKg = scanner.nextInt();

            Cake cake = new Cake(idOfTheCake, newFlavor, newPrice, newKg);
            try {
                cakeService.updateCake(cake);
                System.out.println("Cake updated");
                //printAllCakes();
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }

        private void updateOrder () {
            System.out.println("Enter order Id to update: ");
            int idOfTheOrder = scanner.nextInt();


            System.out.println("Enter new customer order's name: ");
            String newCustomerName = scanner.next();
            System.out.println("Enter new order flavour: ");
            String newFlavor = scanner.next();

            Orders newOrder = new Orders(idOfTheOrder, newFlavor, newCustomerName);
            try {
                serviceOrders.updateOrder(newOrder);
                System.out.println("Order updated");
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }

        }

        private void filterCakesByFlavor () {
            System.out.println("Enter cake flavour: ");
            String flavorOfTheCake = scanner.next();
            AbstractFilter CakeFlavor = new FilterCakesByFlavor(flavorOfTheCake);
            try {
                System.out.println("Cakes with flavor " + flavorOfTheCake + ": ");
                cakeService.filterCakes(CakeFlavor).forEach(System.out::println);
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }

        }

        private void filterOrdersByCustomerName () {
            System.out.println("Enter customer name: ");
            String customerNameOfTheCake = scanner.next();
            AbstractFilter nameFilter = new FilterOrdersByCustomerName(customerNameOfTheCake);
            try {
                System.out.println("Orders with name " + customerNameOfTheCake + ": ");
                serviceOrders.filterOrders(nameFilter).forEach(System.out::println);
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }

        private void filterCakesByPrice () {
            System.out.println("Enter cake's price: ");
            Integer priceOfTheCake = scanner.nextInt();
            AbstractFilter priceFilter = new FilterCakesByPrice(priceOfTheCake);
            try {
                System.out.println("Cakes with price > " + priceOfTheCake + " : ");
                cakeService.filterCakes(priceFilter).forEach(System.out::println);
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }

        }

        private void filterOrdersByCakesFlavor () {
            System.out.println("Enter order flavour: ");
            String flavourOfTheCake = scanner.next();
            AbstractFilter flavorFilter = new FilterOrderByCakeFlavor(flavourOfTheCake);
            try {
                System.out.println("Orders with flavour " + flavourOfTheCake + " : ");
                serviceOrders.filterOrders(flavorFilter).forEach(System.out::println);
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }

        private void printACake () throws RepositoryException {
            System.out.println("Enter cake Id: ");
            int idOfTheCake = scanner.nextInt();
            try{
            Optional<Cake> cake = cakeService.getCakeById(idOfTheCake);
            cake.isPresent();
                System.out.println(cake.get());

            }catch (RepositoryException e){
                System.out.println(e.getMessage());
            }
        }

        private void printAnOrder () throws RepositoryException {
            System.out.println("Enter order Id: ");
            int idOfTheOrder = scanner.nextInt();
            try {
                Optional<Orders> order = serviceOrders.getOrderById(idOfTheOrder);
                order.isPresent();
                System.out.println(order.get());
            }catch (RepositoryException e){
                System.out.println(e.getMessage());
            }
        }

        private void printAllCakes () {
            System.out.println("Cake list: ");
            cakeService.getAllCakes().forEach(System.out::println);
        }

        private void printAllOrders () {
            System.out.println("Order list: ");
            serviceOrders.getAllOrders().forEach(System.out::println);
        }

        private void addCakeToFile () throws IOException, RepositoryException {
            System.out.println("Enter cake Id: ");
            int idOfTheCake = scanner.nextInt();
            System.out.println("Enter cake flavour: ");
            String newFlavor = scanner.next();
            System.out.println("Enter the price: ");
            int newPrice = scanner.nextInt();
            System.out.println("Enter the kg of the cake: ");
            int newKg = scanner.nextInt();
            Cake c1 = new Cake(idOfTheCake, newFlavor, newPrice, newKg);

            FileReader fileReader = null;

            try {
                fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
                Properties properties = new Properties();
                properties.load(fileReader);

                String type = properties.getProperty("type");
                String filepath = properties.getProperty("cakesPath");
                System.out.println(type);

                if (type.equals("text")) {
                    try (FileWriter writer = new FileWriter(filepath, true)) {
                        writer.write(c1.getId() + "," + c1.getFlavor() + "," + c1.getPrice() + "," + c1.getKg() + "\n");
                        System.out.printf("Added to file");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (type.equals("bin")) {
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath, true))) {
                        oos.writeObject(c1);
                        System.out.println("Cake added to binary file successfully.");
                    } catch (IOException e) {
                        System.out.println("Error writing to binary file: " + e.getMessage());
                    }
                }else if(type.equals("db")){
                    try {
                        CakesDBRepository cakesDBRepository = new CakesDBRepository();
                        cakesDBRepository.add(c1);
                        System.out.println("Cake added to database successfully.");
                        CakeService newCakeService = new CakeService(cakesDBRepository);
                        newCakeService.getAllCakes().forEach(System.out::println);
                    }catch(RepositoryException e){
                        System.out.println(e.getMessage());
                    }
                }
                else {
                    System.out.println("Unsupported file type in settings. Use 'text' or 'bin'.");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void addOrderToFile () {
            System.out.println("Enter cake Id: ");
            int idOfTheCake = scanner.nextInt();
            System.out.println("Enter customer name: ");
            String newCustomerName = scanner.next();
            System.out.println("Enter cake flavour: ");
            String newFlavor = scanner.next();
            Orders order1 = new Orders(idOfTheCake, newCustomerName, newFlavor);
            FileReader fileReader = null;
            try {
                fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
                Properties properties = new Properties();
                properties.load(fileReader);

                String type = properties.getProperty("type");
                String filepath = properties.getProperty("ordersPath");
                if (type.equals("text")) {
                    try (FileWriter writer = new FileWriter(filepath, true)) {
                        writer.write(order1.getId() + "," + order1.getCustomerName() + "," + order1.getCakeOrderFlavor() + "\n");
                        System.out.printf("Added to file");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (type.equals("bin")) {
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath, true))) {
                        oos.writeObject(order1);
                        System.out.println("Order added to binary file successfully.");
                    } catch (IOException e) {
                        System.out.println("Error writing to binary file: " + e.getMessage());
                    }
                } else if(type.equals("db")){
                    try {
                        OrdersDBRepository ordersDBRepository = new OrdersDBRepository();
                        ordersDBRepository.add(order1);
                        System.out.println("Cake added to database successfully.");

                        ServiceOrders newOrderService = new ServiceOrders(ordersDBRepository);
                        newOrderService.getAllOrders().forEach(System.out::println);
                    }catch(RepositoryException e){
                        System.out.println(e.getMessage());
                    }

                }else {
                    System.out.println("Unsupported file type in settings. Use 'text' or 'binary'.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void printCakesFromFile () throws IOException {
            FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
            Properties properties = new Properties();
            properties.load(fileReader);

            String type = properties.getProperty("type");
            if(type.equals("text")){
            System.out.println("Cake list: ");
            try (BufferedReader br = new BufferedReader(new FileReader("data/cakes.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }}else {
                CakesDBRepository cakesDBRepository = new CakesDBRepository();
                Iterable<Cake> cakes = cakesDBRepository.getAll();

                for (Cake cake : cakes) {
                    System.out.println("ID: " + cake.getId() + ", Flavor: " + cake.getFlavor() +
                            ", Price: " + cake.getPrice() + ", Kg: " + cake.getKg());
                }
            }
        }

        private void printOrdersFromFile () throws IOException {
            FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
            Properties properties = new Properties();
            properties.load(fileReader);

            String type = properties.getProperty("type");
            if(type.equals("text")){
                System.out.println("Order list:");
                try (BufferedReader br = new BufferedReader(new FileReader("data/orders.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
            }}else {
                OrdersDBRepository ordersDBRepository = new OrdersDBRepository();
                Iterable<Orders> orders = ordersDBRepository.getAll();

                for (Orders currentOrder : orders) {
                    System.out.println("ID: " + currentOrder.getId() + ", customer name: "+ currentOrder.getCustomerName()+", flavor: " + currentOrder.getCakeOrderFlavor());
                }
        }}

        private void deleteCakeFromFile () throws IOException {
            System.out.println("Enter cake Id to delete: ");
            int cakeIdToDelete = scanner.nextInt();

            try {
                FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
                Properties properties = new Properties();
                properties.load(fileReader);
                String filepath = properties.getProperty("cakesPath");

                File file = new File(filepath);
                File tempFile = new File(file.getAbsolutePath() + ".tmp");

                BufferedReader reader = new BufferedReader(new FileReader(file));
                PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

                String currentLine;
                boolean deleted = false;

                while ((currentLine = reader.readLine()) != null) {
                    String[] cakeData = currentLine.split(",");
                    int cakeId = Integer.parseInt(cakeData[0]);

                    if (cakeId != cakeIdToDelete) {
                        writer.println(currentLine);
                    } else {
                        deleted = true;
                    }
                }
                reader.close();
                writer.close();
                if (deleted) {
                    // Delete the original file and rename temp file
                    if (file.delete()) {
                        if (tempFile.renameTo(file)) System.out.println("Cake deleted successfully.");
                         else System.out.println("Failed to rename temp file.");
                    } else System.out.println("Failed to delete original file.");
                } else {
                    System.out.println("Cake not found.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void deleteOrderFromFile() {
        System.out.println("Enter order Id to delete: ");
        int orderIdToDelete = scanner.nextInt();

        try {
            FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a3-deea308\\settings.properties");
            Properties properties = new Properties();
            properties.load(fileReader);
            String filepath = properties.getProperty("ordersPath");

            File file = new File(filepath);
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String currentLine;
            boolean deleted = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] orderData = currentLine.split(",");
                int orderId = Integer.parseInt(orderData[0]);

                if (orderId != orderIdToDelete) {
                    writer.println(currentLine);
                } else {
                    deleted = true;
                }
            }
            reader.close();
            writer.close();

            if (deleted) {
                // Delete the original file and rename temp file
                if (file.delete()) {
                    if (tempFile.renameTo(file)) System.out.println("Order deleted successfully.");
                     else System.out.println("Failed to rename temp file.");
                } else System.out.println("Failed to delete original file.");
            } else System.out.println("Order not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private void updateCakeInFile() {
        System.out.println("Enter cake Id to update: ");
        int cakeIdToUpdate = scanner.nextInt();

        System.out.println("Enter new flavor: ");
        String newFlavor = scanner.next();
        System.out.println("Enter new price: ");
        int newPrice = scanner.nextInt();
        System.out.println("Enter new kg: ");
        int newKg = scanner.nextInt();

        try {
            FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
            Properties properties = new Properties();
            properties.load(fileReader);
            String filepath = properties.getProperty("cakesPath");

            File file = new File(filepath);
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String currentLine;
            boolean updated = false;
            while ((currentLine = reader.readLine()) != null) {
                String[] cakeData = currentLine.split(",");
                int cakeId = Integer.parseInt(cakeData[0]);

                if (cakeId == cakeIdToUpdate) {
                    writer.println(cakeId + "," + newFlavor + "," + newPrice + "," + newKg);
                    updated = true;
                } else {
                    writer.println(currentLine);
                }
            }
            reader.close();
            writer.close();
            if (updated) {
                // Delete the original file and rename temp file
                if (file.delete()) {
                    if (tempFile.renameTo(file)) System.out.println("Cake updated successfully.");
                    else System.out.println("Failed to rename temp file.");
                } else System.out.println("Failed to delete original file.");
            } else System.out.println("Cake not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private void updateOrderInFile() {
        System.out.println("Enter order Id to update: ");
        int orderIdToUpdate = scanner.nextInt();

        System.out.println("Enter new customer name: ");
        String newCustomerName = scanner.next();
        System.out.println("Enter new order flavor: ");
        String newFlavor = scanner.next();

        try {
            FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a3-deea308\\settings.properties");
            Properties properties = new Properties();
            properties.load(fileReader);
            String filepath = properties.getProperty("ordersPath");

            File file = new File(filepath);
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String currentLine;
            boolean updated = false;
            while ((currentLine = reader.readLine()) != null) {
                String[] orderData = currentLine.split(",");
                int orderId = Integer.parseInt(orderData[0]);

                if (orderId == orderIdToUpdate) {
                    writer.println(orderId + "," + newCustomerName + "," + newFlavor);
                    updated = true;
                } else {
                    writer.println(currentLine);
                }
            }
            reader.close();
            writer.close();
            if (updated) {
                // Delete the original file and rename temp file
                if (file.delete()) {
                    if (tempFile.renameTo(file)) System.out.println("Order updated successfully.");
                     else System.out.println("Failed to rename temp file.");
                } else System.out.println("Failed to delete original file.");
            } else System.out.println("Order not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final int CAKE_REPORT1=1;
    private static final int CAKE_REPORT2=2;
    private static final int CAKE_REPORT3=3;
    private static final int CAKE_REPORT4=4;
    private static final int CAKE_REPORT5=5;
    private static final int EXIT_CAKES_REPORTS=0;
    public void printCakesReport(){
        System.out.println("\n Cakes reports: ");
        System.out.println(CAKE_REPORT1+" Cake with same flavor, ascending by price");
        System.out.println(CAKE_REPORT2+" Cake with same kg, descending by flavor");
        System.out.println(CAKE_REPORT3+" Cake with same price, ascending by kg");
        System.out.println(CAKE_REPORT4+" Cake with same flavor, ascending by kg");
        System.out.println(CAKE_REPORT5+" Cake with same price, ascending by flavor");
        System.out.println(EXIT_CAKES_REPORTS+" Exit");
    }
    private void handleCakes(){
        while(true){
            printCakesReport();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case CAKE_REPORT1->{
                    System.out.println("Enter a flavor: ");
                    String flavor = scanner.nextLine();
                    System.out.println("Cakes with flavor " + flavor + " sorted ascending by price");
                    cakeService.allCakesWithTheSameFlavor_ascendingByPrice(flavor).forEach(System.out::println);
                }
                case CAKE_REPORT2->{
                    System.out.println("Enter a kg: ");
                    int kg = scanner.nextInt();
                    System.out.println("Cakes with kg "+kg+" sorted descending by flavor");
                    cakeService.allCakesWithTheSameKg_descendingByFlavor(kg).forEach(System.out::println);
                }
                case CAKE_REPORT3->{
                    System.out.println("Enter a price: ");
                    int price = scanner.nextInt();
                    System.out.println("Cakes with price "+price+" sorted ascending by kg");
                    cakeService.allCakesWithTheSamePrice_ascendingByKg(price).forEach(System.out::println);
                }
                case CAKE_REPORT4->{
                    System.out.println("Enter a flavor: ");
                    String flavor= scanner.nextLine();
                    System.out.println("Cakes with flavor "+flavor+" sorted ascending by kg");
                    cakeService.allCakesWithTheSameFlavor_ascendingByKg(flavor).forEach(System.out::println);
                }
                case CAKE_REPORT5->{
                    System.out.println("Enter a price: ");
                    int price = scanner.nextInt();
                    System.out.println("Cakes with price "+price+" sorted ascending by flavor");
                    cakeService.allCakesWithTheSamePrice_acendingByFlavor(price).forEach(System.out::println);
                }
                case EXIT_CAKES_REPORTS -> {return;}
                default-> System.out.println("invalid option, try again");
            }
        }
    }

    private void deleteCakeFromDatabase() throws IOException {
        FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
        Properties properties = new Properties();
        properties.load(fileReader);

        String type = properties.getProperty("type");
        if(type.equals("db")){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter cake ID to delete: ");
            int cakeId = scanner.nextInt();

            CakesDBRepository cakesDBRepository = new CakesDBRepository();
            try {
                cakesDBRepository.delete(cakeId);
            } catch (RepositoryException e) {
                System.out.println("Error deleting the cake: " + e.getMessage());
            }
        }
    }

    private void deletOrderFromDatabase() throws IOException {
        FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
        Properties properties = new Properties();
        properties.load(fileReader);

        String type = properties.getProperty("type");
        if(type.equals("db")){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter cake ID to delete: ");
            int cakeId = scanner.nextInt();

            try {
                OrdersDBRepository ordersDBRepository = new OrdersDBRepository();
                ordersDBRepository.delete(cakeId);
            } catch (RepositoryException e) {
                System.out.println("Error deleting the cake: " + e.getMessage());
            }
        }
    }

    private void updateCakeFromDatabase() throws IOException {
        FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
        Properties properties = new Properties();
        properties.load(fileReader);

        String type = properties.getProperty("type");
        if (type.equals("db")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter cake ID to update: ");
            int cakeId = scanner.nextInt();

            System.out.print("Enter new flavor: ");
            String flavor = scanner.next();
            System.out.print("Enter new price: ");
            int price = scanner.nextInt();
            System.out.print("Enter new kg: ");
            int kg = scanner.nextInt();

            try {
                CakesDBRepository cakesDBRepository = new CakesDBRepository();
                Cake updatedCake = new Cake(cakeId, flavor, price, kg);
                cakesDBRepository.update(cakeId, updatedCake);
                System.out.println("Cake with ID " + cakeId + " updated successfully in the database.");
            } catch (RepositoryException e) {
                System.out.println("Error updating the cake: " + e.getMessage());
            }
        } else System.out.println("The system is not configured for updating from the database.");
    }

    private void updateOrdersFromDatabase() throws IOException {
        FileReader fileReader = new FileReader("D:\\Facultate\\MAP\\a4-2024-deea308\\settings.properties");
        Properties properties = new Properties();
        properties.load(fileReader);

        String type = properties.getProperty("type");
        if (type.equals("db")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter order ID to update: ");
            int orderId = scanner.nextInt();
            System.out.print("Enter new customer name: ");
            String customerName = scanner.next();

            System.out.print("Enter new flavor: ");
            String flavor = scanner.next();
            try {
                Orders order= new Orders(orderId, customerName, flavor);
                OrdersDBRepository ordersDBRepository = new OrdersDBRepository();
                ordersDBRepository.update(orderId, order);  // This calls the update method in CakesDBRepository
                System.out.println("Cake with ID " + orderId + " updated successfully in the database.");
            } catch (RepositoryException e) {
                System.out.println("Error updating the cake: " + e.getMessage());
            }
        } else System.out.println("The system is not configured for updating from the database.");
    }

}
