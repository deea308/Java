package repository.file;

import domain.Orders;
import repository.RepositoryException;

import java.io.*;
import java.util.HashMap;

public class BinaryOrdersFileRepository extends FileRepository<Integer, Orders> {
    public BinaryOrdersFileRepository(String filename) throws RepositoryException {
        super(filename);
    }

    @Override
    protected void readFromFile() throws RepositoryException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.filename))) {
            // Reading the object from the binary file and casting it to the HashMap
            this.entities = (HashMap<Integer, Orders>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RepositoryException("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new RepositoryException("Error reading file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RepositoryException("Class not found: " + e.getMessage());
        }
    }

    @Override
    protected void writeToFile() throws RepositoryException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.filename))) {
            // Writing the HashMap containing Cake objects to the binary file
            objectOutputStream.writeObject(this.entities);
        } catch (IOException e) {
            throw new RepositoryException("Error writing to file: " + e.getMessage());
        }
    }
    }

