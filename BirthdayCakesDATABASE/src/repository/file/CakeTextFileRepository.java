package repository.file;

import domain.Cake;
import repository.RepositoryException;

import java.io.*;

public class CakeTextFileRepository extends FileRepository<Integer, Cake> {

    public CakeTextFileRepository(String filename) throws RepositoryException {
        super(filename);
    }

    @Override
    protected void readFromFile() throws RepositoryException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 4)
                    continue;
                Integer id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                Integer price = Integer.parseInt(tokens[2]);
                Integer quantity = Integer.parseInt(tokens[3]);
                Cake cake = new Cake(id, name, price, quantity);
                try {
                    super.add(cake);
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
            for (Cake cake : super.findAll())
                bw.write(cake.getId() + "," + cake.getFlavor() + "," + cake.getPrice() + "," + cake.getKg() + "\n");

        } catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }




}

