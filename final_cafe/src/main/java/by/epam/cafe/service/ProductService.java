package by.epam.cafe.service;

import by.epam.cafe.dao.impl.OrderDao;
import by.epam.cafe.dao.impl.ProductDao;
import by.epam.cafe.entity.impl.Product;

import java.sql.Connection;
import java.util.List;

public class ProductService {

    private ProductDao productDao;

    public ProductService(Connection cn) {
        productDao = new ProductDao(cn);
    }



    public List<Product> findAll() {
        return productDao.findAll();
    }


    public Product findEntityById(Long id) {
        return productDao.findEntityById(id);
    }


    public boolean delete(Long id) {
        return productDao.delete(id);
    }


    public boolean delete(Product entity) {
        return productDao.delete(entity);
    }


    public boolean create(Product entity) {
        return productDao.create(entity);
    }


    public boolean update(Product entity) {
        return productDao.update(entity);
    }
}
