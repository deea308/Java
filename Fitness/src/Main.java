import domain.CardioMachine;
import domain.Equipment;
import domain.StrenghtMachine;
import repository.EquipmentRepo;
import service.EquipmentService;

import UI.UI;

public class Main{
    public static void main(String[] args){
        EquipmentRepo<Equipment> equipmentRepo = new EquipmentRepo<>();

        CardioMachine c1= new CardioMachine(1,true,34);
        CardioMachine c2= new CardioMachine(2,false,24);
        CardioMachine c3= new CardioMachine(3,true,67);
        CardioMachine c4= new CardioMachine(4,true,1);

        equipmentRepo.add(c1);
        equipmentRepo.add(c2);
        equipmentRepo.add(c3);
        equipmentRepo.add(c4);

        StrenghtMachine s1= new StrenghtMachine(1,true,34,"arms");
        StrenghtMachine s2= new StrenghtMachine(2,false,224,"legs");
        StrenghtMachine s3= new StrenghtMachine(3,true,14,"core");
        StrenghtMachine s4= new StrenghtMachine(4,true,7,"arms");

        equipmentRepo.add(s1);
        equipmentRepo.add(s2);
        equipmentRepo.add(s3);
        equipmentRepo.add(s4);

        EquipmentService service= new EquipmentService(equipmentRepo);
        UI console= new UI(service);
        console.show();
    }
}