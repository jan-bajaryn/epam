package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.mysql.impl.ProductGroupMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements by.epam.cafe.service.ProductService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductMysqlDao userMysqlDao = dAOFactory.getProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();


    @Override
    public List<Product> findAll() {
        List<Product> all = userMysqlDao.findAll();
        all.forEach(this::buildProduct);
        return all;
    }

    @Override
    public void buildProduct(Product p) {
        ProductGroup productGroup = p.getProductGroup();
        if (productGroup != null) {
            Integer id = productGroup.getId();
            p.setProductGroup(productGroupMysqlDao.findEntityById(id));
        }
    }

    @Override
    public Product findEntityById(Integer integer) {
        Product entityById = userMysqlDao.findEntityById(integer);
        buildProduct(entityById);
        return entityById;
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
    public Product create(Product entity) {
        return userMysqlDao.create(entity);
    }

    @Override
    public boolean update(Product entity) {
        return userMysqlDao.update(entity);
    }


    //TODO make additional query for that if teacher say
    @Override
    public List<Product> findAllByProductGroupNull() {
        return findAll().stream()
                .filter(p -> p.getProductGroup() == null)
                .collect(Collectors.toList());
    }
}
