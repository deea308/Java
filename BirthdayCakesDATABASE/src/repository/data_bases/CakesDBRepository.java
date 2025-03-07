package repository.data_bases;

import domain.Cake;
import repository.CakeRepository;
import repository.RepositoryException;

import java.sql.*;

public class CakesDBRepository extends CakeRepository {
    public static String JDBC_URL = "jdbc:sqlite:BirthdayCakeDB.sqlite";
    final int cake_id=1;
    final int flavor_cake=2;
    final int price_cake=3;
    final int kg_cake=4;

    public CakesDBRepository() {
        try( Connection conn = DriverManager.getConnection(JDBC_URL)){
           PreparedStatement statement= conn.prepareStatement("SELECT * FROM cakes");
           ResultSet resultSet= statement.executeQuery();
           while(resultSet.next()){
               Integer id = resultSet.getInt("id");
               String flavor = resultSet.getString("flavor");
               Integer price = resultSet.getInt("price");
               Integer kg = resultSet.getInt("kg");
               Cake cake = new Cake(id, flavor, price, kg);
               super.add(cake);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Cake cake) throws RepositoryException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            // Check if the cake already exists in the database
            PreparedStatement checkStatement = connection.prepareStatement("SELECT id FROM cakes WHERE id = ?");
            checkStatement.setInt(1, cake.getId());
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                throw new RepositoryException("Object with id " + cake.getId() + " already exists");
            }

            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO cakes VALUES (?, ?, ?, ?)");
            insertStatement.setInt(cake_id, cake.getId());
            insertStatement.setString(flavor_cake, cake.getFlavor());
            insertStatement.setInt(price_cake, cake.getPrice());
            insertStatement.setInt(kg_cake, cake.getKg());
            insertStatement.executeUpdate();

            super.add(cake);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Cake> getAll(){
        this.entities.clear();
        try(Connection connection=DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM cakes");
            ResultSet resultSet= statement.executeQuery();
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String flavor = resultSet.getString("flavor");
                Integer price = resultSet.getInt("price");
                Integer kg = resultSet.getInt("kg");
                Cake cake = new Cake(id, flavor, price, kg);
                super.add(cake);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return entities.values();
    }

    @Override
    public void update(Integer id, Cake cake) throws RepositoryException {
        super.update(id, cake);
        try(Connection connection= DriverManager.getConnection(JDBC_URL)){
            String newQuery="UPDATE cakes SET flavor=?,price=?,kg=? WHERE id=?";
            try(PreparedStatement statement= connection.prepareStatement(newQuery) ){
                statement.setString(1, cake.getFlavor());
                statement.setInt(2, cake.getPrice());
                statement.setInt(3, cake.getKg());
                statement.setInt(4, cake.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Integer id) throws RepositoryException {
        super.delete(id);
        try(Connection connection=DriverManager.getConnection(JDBC_URL)){
            PreparedStatement statement=connection.prepareStatement("DELETE FROM cakes WHERE id=?");
            final int idFieldToBeDeleted=1;
            statement.setInt(idFieldToBeDeleted, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
