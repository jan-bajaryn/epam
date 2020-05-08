package by.epam.cafe.service;

import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws ServiceException;

    List<Product> findAllByPart(int part) throws ServiceException;

    Product findEntityById(Integer integer) throws ServiceException;

    boolean deleteById(Integer integer) throws ServiceException;

    boolean delete(Product entity) throws ServiceException;

    Product create(Product entity) throws ServiceException;

    boolean update(Product entity) throws ServiceException;

    void buildProduct(Product p, Transaction transaction);

    List<Product> findAllByProductGroupNotDisabled() throws ServiceException;

    List<Product> findAllByProductGroupNull() throws ServiceException;

    int count() throws ServiceException;

}
