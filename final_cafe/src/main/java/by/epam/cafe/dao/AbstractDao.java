package by.epam.cafe.dao;

import by.epam.cafe.entity.Entity;

import java.util.List;

public interface AbstractDao<ID, T extends Entity<ID>> {

    List<T> findAll();

    T findEntityById(ID id);

    boolean deleteById(ID id);

    boolean delete(T entity);

    T create(T entity);

    boolean update(T entity);
}
