package repository;

import java.util.ArrayList;
import java.util.List;

public class EquipmentRepo<T> {
    private List<T> list=new ArrayList<>();
    public void add(T equipment){
        if(list.contains(equipment)){
            throw new IllegalArgumentException("Already exists");
        }
        list.add(equipment);
    }
    public List<T> getList(){
        return list;
    }
}
