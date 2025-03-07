package UI;

import domain.CardioMachine;
import domain.StrenghtMachine;
import service.EquipmentService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class UI {
    public EquipmentService service;
    public Scanner scanner;

    public UI(EquipmentService service) {
        this.scanner = new Scanner(System.in);
        this.service = service;
    }

    void printMenu(){
        System.out.println("1.Add a new equipment");
        System.out.println("2.Print all equipment");
        System.out.println("3.Sorted by price and min level");
        System.out.println("4.Save to file");
        System.out.println("5.Exit");
    }

    public void show(){
        printMenu();
        while(true){
            System.out.println("Choose an option: ");
            int option = scanner.nextInt();
            switch(option){
                case 1:
                    addAnEquipment();
                    break;
                case 2:
                    printAll();
                    break;
                case 3:
                    sorted();
                    break;
                case 4:
                    printToFile();
                case 5:
                    System.exit(0);
                default: System.out.println("Invalid option");
            }
        }
    }
    public void addAnEquipment(){
        System.out.println("Choose an equipment: Cardio/Strength");
        String option1 = scanner.next();
        if(option1.equals("Cardio")){
            System.out.println("Enter the serial number of the equipment: ");
            int serial = scanner.nextInt();
            System.out.println("Enter the value of the equipment: ");
            boolean value = scanner.nextBoolean();
            System.out.println("Enter a resistance:");
            int resistance = scanner.nextInt();
            try {
                CardioMachine c1 = new CardioMachine(serial, value, resistance);
                service.add(c1);
                System.out.println("Added successfully");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        else if(option1.equals("Strength")){
            System.out.println("Enter the serial number of the equipment: ");
            int serial = scanner.nextInt();
            System.out.println("Enter the value of the equipment: ");
            boolean value = scanner.nextBoolean();
            System.out.println("Enter a motion level:");
            int motionLevel = scanner.nextInt();
            System.out.println("Enter the muscle group: ");
            String muscleGroup = scanner.next();
            try{
                StrenghtMachine s1= new StrenghtMachine(serial,value,motionLevel,muscleGroup);
                service.add(s1);
                System.out.println("Added successfully");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
    public void printAll(){
        System.out.println("List of equipments:");
        service.findAll().forEach(System.out::println);
    }

    public void sorted(){
        System.out.println("Enter maximum price: ");
        int max = scanner.nextInt();
        System.out.println("Enter min level: ");
        int min = scanner.nextInt();
        service.getCardioEquipmentSorted(max,min).forEach(System.out::println);
    }

    public void printToFile(){
        try{
            System.out.println("enter the path to the properties file: ");
            String path = scanner.next();

            //load the properties file
            Properties properties=new Properties();
            try(FileInputStream input = new FileInputStream(path)){
                properties.load(input);

            }catch(Exception e){
                System.out.println(e.getMessage());
            }

            //fetch the file path from the properties file
            boolean filevalue= properties.containsKey("filevalue");
            String filePath = properties.getProperty("file_path");
            if(path==null){
                System.out.println("Invalid path.");
                return;
            }
            service.saveEquipmentNeededMaintenance(filevalue,filePath);
            System.out.println("Equipment added successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
