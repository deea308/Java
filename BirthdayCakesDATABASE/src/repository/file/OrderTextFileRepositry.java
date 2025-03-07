package repository.file;

import domain.Orders;
import repository.RepositoryException;

import java.io.*;

public class OrderTextFileRepositry extends FileRepository<Integer, Orders> {
    public OrderTextFileRepositry(String filename) throws RepositoryException {
        super(filename);
    }

    @Override
    protected void readFromFile() throws RepositoryException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 3)
                    continue;
                Integer id = Integer.parseInt(tokens[0]);
                String customerName = tokens[1];
                String flavor = tokens[2];
                Orders order = new Orders(id, customerName, flavor);
                try {
                    super.add(order);
                } catch (RepositoryException e) {
                    throw new RepositoryException(e.getMessage());
                }
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

        }
    }

    @Override
    protected void writeToFile() throws RepositoryException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename))) {
            for (Orders currentOrder : super.findAll()) {
                bw.write(currentOrder.getId() + "," + currentOrder.getCustomerName() + "," + currentOrder.getCakeOrderFlavor() + "\n");
            }
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
