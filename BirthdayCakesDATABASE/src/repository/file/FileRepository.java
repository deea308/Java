package repository.file;

import domain.Entity;
import repository.MemoryRepository;
import repository.RepositoryException;

import java.util.ArrayList;

public abstract class FileRepository<ID, T extends Entity<ID>> extends MemoryRepository<ID, T> {
    protected String filename;


    public FileRepository(String filename) throws RepositoryException {
        this.filename = filename;
        this.readFromFile();
    }

    protected abstract void readFromFile() throws RepositoryException;

    protected abstract void writeToFile() throws RepositoryException;

    public ArrayList<T> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public void add( T entity) throws RepositoryException {
        super.add(entity);
        writeToFile();
    }

    @Override
    public void delete(ID id) throws RepositoryException {
        super.delete(id);
                writeToFile();
    }

    @Override
    public void update(ID id,T entity) throws RepositoryException {
        super.update(id, entity);
        writeToFile();
    }
}
