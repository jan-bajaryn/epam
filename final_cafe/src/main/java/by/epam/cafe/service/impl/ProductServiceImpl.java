package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.impl.Product;

import java.util.List;

public class ProductServiceImpl implements by.epam.cafe.service.ProductService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductMysqlDao userMysqlDao = dAOFactory.getProductMysqlDao();


    @Override
    public List<Product> findAll() {
        return userMysqlDao.findAll();
    }

    @Override
    public Product findEntityById(Integer integer) {
        return userMysqlDao.findEntityById(integer);
    }

    @Override
    public boolean deleteById(Integer integer) {
        return userMysqlDao.deleteById(integer);
    }

    @Override
    public boolean delete(Product entity) {
        return userMysqlDao.delete(entity);
    }

    @Override
    public boolean create(Product entity) {
        return userMysqlDao.create(entity);
    }

    @Override
    public boolean update(Product entity) {
        return userMysqlDao.update(entity);
    }
}
