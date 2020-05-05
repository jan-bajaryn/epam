package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.dao.mysql.impl.ProductGroupMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements by.epam.cafe.service.ProductService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductMysqlDao userMysqlDao = dAOFactory.getProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();


    @Override
    public List<Product> findAll() throws ServiceException {

        try (final Transaction transaction = dAOFactory.createTransaction()) {
            List<Product> all = userMysqlDao.findAll(transaction);
            all.forEach(p -> buildProduct(p, transaction));
            return all;
        } catch (DaoException e) {
            throw new ServiceException();
        }

    }

    @Override
    public void buildProduct(Product p, Transaction transaction) {
        ProductGroup productGroup = p.getProductGroup();
        if (productGroup != null) {
            Integer id = productGroup.getId();
            p.setProductGroup(productGroupMysqlDao.findEntityById(id, transaction));
        }
    }

    @Override
    public Product findEntityById(Integer integer) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            Product entityById = userMysqlDao.findEntityById(integer, transaction);
            buildProduct(entityById, transaction);
            return entityById;
        } catch (DaoException e) {
            throw new ServiceException();
        }

    }

    @Override
    public boolean deleteById(Integer integer) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userMysqlDao.deleteById(integer, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public boolean delete(Product entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userMysqlDao.delete(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public Product create(Product entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            Product product = userMysqlDao.create(entity, transaction);
            if (product != null) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return product;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public boolean update(Product entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userMysqlDao.update(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }


    //TODO make additional query for that if teacher say
    @Override
    public List<Product> findAllByProductGroupNull() throws ServiceException {
        return findAll().stream()
                .filter(p -> p.getProductGroup() == null)
                .collect(Collectors.toList());
    }
}
