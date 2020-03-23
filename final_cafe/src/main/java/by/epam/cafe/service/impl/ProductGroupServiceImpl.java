package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.dao.mysql.impl.ProductGroupMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.exception.NullServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class ProductGroupServiceImpl implements by.epam.cafe.service.ProductGroupService {

    private static final Logger log = LogManager.getLogger(ProductGroupServiceImpl.class);


    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductMysqlDao productMysqlDao = dAOFactory.getProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();


    @Override
    public List<ProductGroup> findAll() throws NullParamDaoException {
        List<ProductGroup> all = productGroupMysqlDao.findAll();
        for (ProductGroup productGroup : all) {
            buildProductGroup(productGroup);
        }
        return all;
    }

    @Override
    public ProductGroup findEntityById(Integer integer) throws NullParamDaoException {
        ProductGroup entityById = productGroupMysqlDao.findEntityById(integer);
        buildProductGroup(entityById);
        return entityById;
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

    @Override
    public List<ProductGroup> findAllByProductTypeNotDisabled(ProductType type) throws NullServiceException {
        try {
            List<ProductGroup> list = productGroupMysqlDao.findAllByProductTypeAndDisabled(type, false);
            for (ProductGroup productGroup : list) {
                buildProductGroup(productGroup);
            }
            return list;
        } catch (NullParamDaoException e) {
            throw new NullServiceException(e);
        }
    }


    //TODO at that time not useful, may be delete after exit
    @Override
    public List<ProductGroup> findAllEmpty() {
        return productGroupMysqlDao.findAllEmpty();
    }

    @Override
    public List<ProductGroup> findAllExcept(ProductGroup productGroup) throws NullParamDaoException {
        List<ProductGroup> all = findAll();
        if (productGroup != null) {
            all = all.stream()
                    .filter(p -> !p.getId().equals(productGroup.getId()))
                    .collect(Collectors.toList());
        }
        return all;
    }

    private void buildProductGroup(ProductGroup productGroup) throws NullParamDaoException {
        List<Product> products = productGroup.getProducts();
        List<Product> allByProductGroupId = productMysqlDao.findAllByProductGroupId(productGroup.getId());
        products.addAll(allByProductGroupId);
        log.info("buildProductGroup: products = {}", products);
    }
}
