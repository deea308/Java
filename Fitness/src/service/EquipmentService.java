package service;

import domain.CardioMachine;
import domain.Equipment;
import domain.StrenghtMachine;
import repository.EquipmentRepo;

import javax.smartcardio.Card;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.CryptoPrimitive;
import java.util.Comparator;
import java.util.List;

public class EquipmentService {
    public EquipmentRepo<Equipment> equipmentRepo ;

    public EquipmentService(EquipmentRepo<Equipment> equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }
    public void add(Equipment equipment) {
        equipmentRepo.add(equipment);
    }
    public List<Equipment> findAll() {
        return equipmentRepo.getList();
    }

    public List<Equipment> getCardioEquipmentSorted(double price, int minLevel) {
        return equipmentRepo.getList().stream()
                .filter(equipment->equipment.computePrice()<price &&
                        ((equipment instanceof CardioMachine && ((CardioMachine)  equipment).getResistance()>minLevel) ||
                                (equipment instanceof StrenghtMachine &&((StrenghtMachine) equipment ).getMotion_lvl()>minLevel)))
        .sorted((e1,e2)->Integer.compare(e2.getSerial_number(),e1.getSerial_number()))
                .toList();

    }
    public void saveEquipmentNeededMaintenance(boolean isregular_maintenance, String filePath)throws IOException {
        List<Equipment> equipmentList = equipmentRepo.getList().stream()
                .filter(equipment -> equipment.isregular_maintenance() == isregular_maintenance)
                .sorted(Comparator.comparingDouble(Equipment::computePrice).reversed())
                .toList();
        try(BufferedWriter writer= new BufferedWriter(new FileWriter(filePath))){
            for(Equipment equipment: equipmentList) {
                writer.write(equipment.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
