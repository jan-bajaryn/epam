package by.epam.cafe.service;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.ProductGroupMysqlDao;
import by.epam.cafe.entity.impl.ProductGroup;

import java.util.List;

public class ProductGroupService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();


    public List<ProductGroup> findAll() {
        return productGroupMysqlDao.findAll();
    }

    public ProductGroup findEntityById(Integer integer) {
        return productGroupMysqlDao.findEntityById(integer);
    }

    public boolean deleteById(Integer integer) {
        return productGroupMysqlDao.deleteById(integer);
    }

    public boolean delete(ProductGroup entity) {
        return productGroupMysqlDao.delete(entity);
    }

    public boolean create(ProductGroup entity) {
        return productGroupMysqlDao.create(entity);
    }

    public boolean update(ProductGroup entity) {
        return productGroupMysqlDao.update(entity);
    }
}
