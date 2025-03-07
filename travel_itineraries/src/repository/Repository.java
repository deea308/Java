package repository;


import domain.Travel;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.HashMap;

public class Repository {
    protected HashMap<Integer, Travel> travels= new HashMap<>();
    public void add(Integer key,Travel travel){
        if(!travels.containsKey(key)){
                travels.put(key, travel);
        }
    }
    public void modify(Integer key, Travel travel) {
        if(travels.containsKey(key))
            travels.put(key,travel);}

    public Travel findById(Integer key) {
        if (travels.containsKey(key))
            return travels.get(key);
        return null;
    }

    public Iterable<Travel> getAll() {
        return  travels.values();
    }

    public void updateInDatabase(Travel travel) {

    }

}
