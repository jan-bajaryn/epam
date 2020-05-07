package by.epam.cafe.dao;

import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.entity.Entity;

import java.util.List;

public interface AbstractDao<ID, T extends Entity<ID>> {

    List<T> findAll(Transaction transaction);

    List<T> findAllByPart(Transaction transaction, int begin, int count) throws DaoException;

    T findEntityById(ID id, Transaction transaction);

    boolean deleteById(ID id, Transaction transaction);

    boolean delete(T entity, Transaction transaction);

    T create(T entity, Transaction transaction);

    boolean update(T entity, Transaction transaction);
}
