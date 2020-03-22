package by.epam.cafe.service;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.impl.Product;

import java.util.List;

public class ProductService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductMysqlDao userMysqlDao = dAOFactory.getProductMysqlDao();


    public List<Product> findAll() {
        return userMysqlDao.findAll();
    }

    public Product findEntityById(Integer integer) {
        return userMysqlDao.findEntityById(integer);
    }

    public boolean deleteById(Integer integer) {
        return userMysqlDao.deleteById(integer);
    }

    public boolean delete(Product entity) {
        return userMysqlDao.delete(entity);
    }

    public boolean create(Product entity) {
        return userMysqlDao.create(entity);
    }

    public boolean update(Product entity) {
        return userMysqlDao.update(entity);
    }
}
