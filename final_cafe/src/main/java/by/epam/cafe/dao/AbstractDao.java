package by.epam.cafe.dao;

import by.epam.cafe.entity.Entity;

import java.util.List;

public interface AbstractDao<K, T extends Entity> {

    List<T> findAll();

    T findEntityById(K id);

    boolean delete(K id);

    boolean delete(T entity);

    boolean create(T entity);

    T update(T entity);

}
