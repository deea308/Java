package repository;

import domain.Travel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBRepository extends Repository {
    public String databaseName;
    public DBRepository(String databaseName) throws SQLException {
        this.databaseName = databaseName;
        try(Connection dbConnection= DriverManager.getConnection("jdbc:sqlite:"+this.databaseName);){
            PreparedStatement preparedStatement=dbConnection.prepareStatement("SELECT * FROM travel");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt(1);
                String name = resultSet.getString(2);
                String continent = resultSet.getString(3);
                String description = resultSet.getString(4);
                String placesString = resultSet.getString(5);
                List<String> places = placesString == null ? new ArrayList<>() :
                        Arrays.asList(placesString.split(","));


                Travel travel = new Travel(id,name,continent,description,places);
                super.add(id,travel);


            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void updateInDatabase(Travel recipe){
        try (Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:" + this.databaseName)) {
            String sql = "UPDATE travel SET description = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = dbConnection.prepareStatement(sql)) {
                preparedStatement.setString(1, recipe.getDescription());
                preparedStatement.setInt(2, recipe.getId());
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
