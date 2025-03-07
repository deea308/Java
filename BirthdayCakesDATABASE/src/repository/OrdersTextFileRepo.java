package repository;

import domain.Orders;
import repository.file.FileRepository;

import java.io.*;

public class OrdersTextFileRepo extends FileRepository<Integer, Orders> {
    public OrdersTextFileRepo(String filename) throws RepositoryException {
        super(filename);
    }

    @Override
    protected void readFromFile() throws RepositoryException {
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader(filename))){
            String line;
            while((line=bufferedReader.readLine())!=null){
                String[] tokens=line.split(",");
                if(tokens.length!=3)
                    continue;
                Integer id= Integer.parseInt(tokens[0]);
                String customerName= tokens[1];
                String orderFlavor= tokens[2];
                Orders order1= new Orders(id,customerName,orderFlavor);
                super.add(order1);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (RepositoryException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    protected void writeToFile() throws RepositoryException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filename))){
            for(Orders order: super.getAll()){
                bufferedWriter.write(order.getId() + ","+ order.getCustomerName() + ","+ order.getCakeOrderFlavor()+"\n");
            }
        }catch(IOException e){
            throw new RepositoryException(e.getMessage());
        }
    }
}
