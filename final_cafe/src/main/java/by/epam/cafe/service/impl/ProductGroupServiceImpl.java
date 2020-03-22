package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.ProductGroupMysqlDao;
import by.epam.cafe.entity.impl.ProductGroup;

import java.util.List;

public class ProductGroupServiceImpl implements by.epam.cafe.service.ProductGroupService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();


    @Override
    public List<ProductGroup> findAll() {
        return productGroupMysqlDao.findAll();
    }

    @Override
    public ProductGroup findEntityById(Integer integer) {
        return productGroupMysqlDao.findEntityById(integer);
    }

    @Override
    public boolean deleteById(Integer integer) {
        return productGroupMysqlDao.deleteById(integer);
    }

    @Override
    public boolean delete(ProductGroup entity) {
        return productGroupMysqlDao.delete(entity);
    }

    @Override
    public boolean create(ProductGroup entity) {
        return productGroupMysqlDao.create(entity);
    }

    @Override
    public boolean update(ProductGroup entity) {
        return productGroupMysqlDao.update(entity);
    }
}
