package repository.data_bases;
import domain.Orders;
import repository.OrderRepository;
import repository.RepositoryException;

import java.sql.*;


public class OrdersDBRepository extends OrderRepository {
    public static String JDBC_URL = "jdbc:sqlite:D:BirthdayCakeDB.sqlite";
    final int order_id=1;
    final int order_customer_name=2;
    final int order_flavor=3;

    public OrdersDBRepository() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer order_id = resultSet.getInt("id");
                String customer_name = resultSet.getString("customer_name");
                String flavor = resultSet.getString("flavor");
                Orders order = new Orders(order_id, customer_name, flavor);
                super.add(order);
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Orders order) throws RepositoryException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement checkStatement = connection.prepareStatement("SELECT id FROM orders WHERE id = ?");
            checkStatement.setInt(1, order.getId());
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                throw new RepositoryException("Object with id " + order.getId() + " already exists");
            }
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO orders VALUES (?, ?,?)");
            insertStatement.setInt(1, order.getId());
            insertStatement.setString(2, order.getCustomerName());
            insertStatement.setString(3, order.getCakeOrderFlavor());

            insertStatement.executeUpdate();
            super.add(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Orders> getAll(){
        this.entities.clear();
        try(Connection connection=DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet= statement.executeQuery();
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
               String customerName = resultSet.getString("customer_name");
               String flavor = resultSet.getString("flavor");
                Orders order = new Orders(id, customerName,flavor);
                super.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        return entities.values();
    }

@Override
public void update(Integer id, Orders order) throws RepositoryException {
    super.update(id, order);
    try (Connection connection = DriverManager.getConnection(JDBC_URL)) {

        PreparedStatement statement = connection.prepareStatement("UPDATE orders SET customer_name = ?, flavor = ? WHERE id = ?");
        statement.setString(1, order.getCustomerName());
        statement.setString(2, order.getCakeOrderFlavor());
        statement.setInt(3, id);
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected == 0) {
            throw new RuntimeException("No rows updated. Order ID might not exist.");
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error updating order in database: " + e.getMessage(), e);
    }
}

    @Override
    public void delete(Integer id) throws RepositoryException {
        super.delete(id);
        try(Connection connection=DriverManager.getConnection(JDBC_URL)){
            PreparedStatement statement=connection.prepareStatement("DELETE FROM orders WHERE id=?");
            final int idFieldToBeDeleted=1;
            statement.setInt(idFieldToBeDeleted, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


