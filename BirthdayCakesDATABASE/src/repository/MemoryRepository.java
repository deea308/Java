package repository;

import domain.Entity;


import java.util.HashMap;


public class MemoryRepository<ID, T extends Entity<ID>> implements IRepository<ID, T> {
    protected HashMap<ID, T> entities=new HashMap<>();

    @Override
    public void add( T entity) throws RepositoryException {
        if(entities.containsKey(entity.getId())){
            throw new RepositoryException("Object with id " +entity.getId() + " already exists");
        }
        entities.put( entity.getId(), entity );
    }

    @Override
    public void update(ID id, T entity)  throws RepositoryException{
        if(!entities.containsKey(entity.getId())){
            throw new RepositoryException("Object with id " +id +" does not exist");
        }
        entities.put(entity.getId(), entity);

    }

    @Override
    public void delete(ID id)  throws RepositoryException{
        if(!entities.containsKey(id)){
            throw new RepositoryException("Object with id " + id +" does not exist");
        }
        entities.remove(id);

    }

    @Override
    public T findByID(ID id) throws RepositoryException {
        if (entities.containsKey(id)) {
            return entities.get(id);
        } else {
            throw new RepositoryException("Object with id " + id + " does not exist");
        }
    }

    @Override
    public Iterable<T> getAll() {
        return entities.values();
    }

    @Override
    public short printAll()  {
        for(T entity : entities.values()){
            System.out.println(entity);
        }
        return 0;
    }
}
