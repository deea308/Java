package repository;


import domain.Entity;

public interface IRepository<ID, T extends Entity<ID>> {
    void add(T entity) throws RepositoryException;
    void update(ID id, T entity) throws RepositoryException;
    void delete(ID id) throws RepositoryException;
    T findByID(ID id) throws RepositoryException;
    Iterable<T> getAll();
    short printAll() ;

}
