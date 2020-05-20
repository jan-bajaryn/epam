package by.epam.cafe.service.db.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.dao.mysql.impl.ProductGroupMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.service.db.ProductService;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

import static by.epam.cafe.config.Configuration.MAX_PAGINATION_ELEMENTS;

public class ProductServiceImpl implements ProductService {

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductMysqlDao productMysqlDao = dAOFactory.getProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();


    /**
     * @return List of all {@link Product} in base
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<Product> findAll() throws ServiceException {

        try (final Transaction transaction = dAOFactory.createTransaction()) {
            List<Product> all = productMysqlDao.findAll(transaction);
            all.forEach(p -> buildProduct(p, transaction));
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    /**
     * @param part number of part of all entities {@link Product} from the database,
     *             where maximum number of entities in one part is
     *             {@value by.epam.cafe.config.Configuration#MAX_PAGINATION_ELEMENTS}
     * @return List of {@link Product} from the database related to part from input
     * or empty list if there no so part in database
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<Product> findAllByPart(int part) throws ServiceException {

        try (final Transaction transaction = dAOFactory.createTransaction()) {
            List<Product> all = productMysqlDao.findAllByPart(transaction,
                    (part - 1) * MAX_PAGINATION_ELEMENTS,
                    MAX_PAGINATION_ELEMENTS);
            all.forEach(p -> buildProduct(p, transaction));
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void buildProduct(Product p, Transaction transaction) {
        ProductGroup productGroup = p.getProductGroup();
        if (productGroup != null) {
            Integer id = productGroup.getId();
            p.setProductGroup(productGroupMysqlDao.findEntityById(id, transaction));
        }
    }

    /**
     * @param id identifier of {@link Product}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    @Override
    public Product findEntityById(Integer id) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            Product entityById = productMysqlDao.findEntityById(id, transaction);
            buildProduct(entityById, transaction);
            return entityById;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    /**
     * @param id identifier of {@link Product}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public boolean deleteById(Integer integer) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = productMysqlDao.deleteById(integer, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param entity what dedicated to delete {@link Product}
     * @return true if entity successfully deleted,
     * otherwise return false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public boolean delete(Product entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = productMysqlDao.delete(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * @param entity {@link Product} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public Product create(Product entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            Product product = productMysqlDao.create(entity, transaction);
            if (product != null) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return product;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param entity {@link Product} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public boolean update(Product entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = productMysqlDao.update(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * @return List of {@link Product} from the database
     * where method {@link Product#getProductGroup()} returns null
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<Product> findAllByProductGroupNull() throws ServiceException {
        return findAll().stream()
                .filter(p -> p.getProductGroup() == null)
                .collect(Collectors.toList());
    }

    /**
     * @return List of {@link Product} from the database
     * where field {@link Product#getProductGroup()} by the method
     * {@link ProductGroup#isDisabled()} returns false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<Product> findAllByProductGroupNotDisabled() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            List<Product> all = productMysqlDao.findAllByProductGroupNotDisabled(transaction);
            all.forEach(p -> buildProduct(p, transaction));
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @return count of {@link Product} in the database
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return productMysqlDao.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
