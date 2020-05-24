package by.epam.cafe.service.db.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.dao.mysql.impl.ProductGroupMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.service.db.ProductGroupService;
import by.epam.cafe.service.exception.NullServiceException;
import by.epam.cafe.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

import static by.epam.cafe.config.Configuration.MAX_PAGINATION_ELEMENTS;

public class ProductGroupServiceImpl implements ProductGroupService {

    private static final Logger log = LogManager.getLogger(ProductGroupServiceImpl.class);

    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductMysqlDao productMysqlDao = dAOFactory.getProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();


    /**
     * @return List of all {@link ProductGroup} in base
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<ProductGroup> findAll() throws ServiceException {

        try (Transaction transaction = dAOFactory.createTransaction()) {
            List<ProductGroup> all = productGroupMysqlDao.findAll(transaction);
            for (ProductGroup productGroup : all) {
                buildProductGroup(productGroup, transaction);
            }
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param part number of part of all entities {@link ProductGroup} from the database,
     *             where maximum number of entities in one part is
     *             {@value by.epam.cafe.config.Configuration#MAX_PAGINATION_ELEMENTS}
     * @return List of {@link ProductGroup} from the database related to part from input
     * or empty list if there no so part in database
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<ProductGroup> findAllByPart(int part) throws ServiceException {

        try (Transaction transaction = dAOFactory.createTransaction()) {
            List<ProductGroup> all = productGroupMysqlDao.findAllByPart(transaction, (part - 1) * MAX_PAGINATION_ELEMENTS, MAX_PAGINATION_ELEMENTS);
            for (ProductGroup productGroup : all) {
                buildProductGroup(productGroup, transaction);
            }
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param id identifier of {@link ProductGroup}
     * @return entity from database identified by id, or {@code null} if
     * there no entity with so id
     * @throws ServiceException if service can't connect to the database
     * @see by.epam.cafe.entity.db.Entity
     */
    @Override
    public ProductGroup findEntityById(Integer id) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            ProductGroup entityById = productGroupMysqlDao.findEntityById(id, transaction);
            if (entityById != null) {
                buildProductGroup(entityById, transaction);
            }
            return entityById;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param entity {@link ProductGroup} dedicated to create
     * @return created entity with new id, or {@code null} if entity can't be created
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public ProductGroup create(ProductGroup entity) throws ServiceException {

        try (Transaction transaction = dAOFactory.createTransaction()) {
            ProductGroup productGroup = productGroupMysqlDao.create(entity, transaction);

            if (productGroup != null) {
                insertProductsIfPossible(productGroup, transaction);
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return productGroup;

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void insertProductsIfPossible(ProductGroup entity, Transaction transaction) {

        List<Product> products = entity.getProducts();
        if (products != null) {
            products.stream()
                    .map(p -> productMysqlDao.findEntityById(p.getId(), transaction))
                    .peek(p -> p.setProductGroup(entity))
                    .forEach(ent -> productMysqlDao.update(ent, transaction));
        }


    }

    /**
     * Updates {@link ProductGroup} in the database by id. If
     * {@link ProductGroup#getPhotoName()} is null, than would be use old value
     *
     * @param entity {@link ProductGroup} dedicated to update identified by id
     *               {@link by.epam.cafe.entity.db.Entity}
     * @return true if entity successfully updated otherwise returns false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public boolean update(ProductGroup entity) throws ServiceException {

        if (entity == null || entity.getId() == null) {
            return false;
        }
        if (!updatePhotoNameIfNeed(entity)) {
            return false;
        }

        try (final Transaction transaction = dAOFactory.createTransaction()) {

            try {
                boolean update = productGroupMysqlDao.update(entity, transaction);
                insertAndDeleteOthers(entity, transaction);

                if (update) {
                    transaction.commit();
                }

                return update;
            } catch (ServiceException e) {
                transaction.rollBack();
                return false;
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean updatePhotoNameIfNeed(ProductGroup entity) throws ServiceException {
        if (entity.getPhotoName() == null) {
            ProductGroup entityById = findEntityById(entity.getId());
            if (entityById != null) {
                entity.setPhotoName(entityById.getPhotoName());
            } else {
                return false;
            }
        }
        return true;
    }

    private void insertAndDeleteOthers(ProductGroup entity, Transaction transaction) throws ServiceException, DaoException {

        try {
            final List<Product> products = entity.getProducts();
            final List<Product> allByProductGroupId = productMysqlDao.findAllByProductGroupId(entity.getId(), transaction);

            List<Product> toDelete = allByProductGroupId.stream()
                    .filter(p -> {
                        for (Product product : products) {
                            if (product.getId().equals(p.getId())) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .collect(Collectors.toList());
            deleteAll(toDelete, transaction);
            List<Product> toAdd = products.stream()
                    .filter(p -> {
                        for (Product product : allByProductGroupId) {
                            if (product.getId().equals(p.getId())) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .collect(Collectors.toList());
            insertAll(toAdd, entity, transaction);
        } catch (NullParamDaoException e) {
            transaction.rollBack();
            throw new ServiceException(e);
        }
    }

    private void deleteAll(List<Product> toDelete, Transaction transaction) {
        for (Product product : toDelete) {
            Product entityById = productMysqlDao.findEntityById(product.getId(), transaction);
            entityById.setProductGroup(null);
            productMysqlDao.update(entityById, transaction);
        }
    }

    private void insertAll(List<Product> toAdd, ProductGroup entity, Transaction transaction) {
        for (Product product : toAdd) {
            Product entityById = productMysqlDao.findEntityById(product.getId(), transaction);
            entityById.setProductGroup(entity);
            productMysqlDao.update(entityById, transaction);
        }

    }

    /**
     * @return List of {@link ProductGroup} from the database
     * where method {@link ProductGroup#isDisabled()} returns false
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<ProductGroup> findAllByProductTypeNotDisabled(ProductType type) throws ServiceException {

        try (final Transaction transaction = dAOFactory.createTransaction()) {
            try {
                List<ProductGroup> list = productGroupMysqlDao.findAllByProductTypeAndDisabled(type, false, transaction);
                for (ProductGroup productGroup : list) {
                    buildProductGroup(productGroup, transaction);
                }
                return list;
            } catch (NullParamDaoException e) {
                throw new NullServiceException(e);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param productGroup {@link ProductGroup} without what should be return list
     * @return List of all {@link ProductGroup} from the database
     * except productGroup from input
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public List<ProductGroup> findAllExcept(ProductGroup productGroup) throws ServiceException {
        List<ProductGroup> all = findAll();
        if (productGroup != null) {
            all = all.stream()
                    .filter(p -> !p.getId().equals(productGroup.getId()))
                    .collect(Collectors.toList());
        }
        return all;
    }


    /**
     * Make {@link ProductGroup} disabled {@link ProductGroup#setDisabled(boolean)} true
     * and commit to the database
     *
     * @param id identifier of {@link ProductGroup}
     * @throws ServiceException                                    if service can't connect to the database
     * @throws by.epam.cafe.service.exception.NullServiceException if id is {@code null}
     * @see by.epam.cafe.entity.db.Entity
     */
    @Override
    public void disableById(Integer id) throws ServiceException {
        if (id == null) {
            throw new NullServiceException("null id");
        }
        try (final Transaction transaction = dAOFactory.createTransaction()) {

            ProductGroup productGroup = productGroupMysqlDao.findEntityById(id, transaction);
            if (!productGroup.isDisabled()) {
                productGroup.setDisabled(true);
                productGroupMysqlDao.update(productGroup, transaction);
                transaction.commit();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * Make {@link ProductGroup} disabled {@link ProductGroup#setDisabled(boolean)} false
     * and than commit to the database
     *
     * @param id identifier of {@link ProductGroup}
     * @throws ServiceException                                    if service can't connect to the database
     * @throws by.epam.cafe.service.exception.NullServiceException if id is {@code null}
     * @see by.epam.cafe.entity.db.Entity
     */
    @Override
    public void enableById(Integer id) throws ServiceException {
        if (id == null) {
            throw new NullServiceException("null id");
        }
        try (final Transaction transaction = dAOFactory.createTransaction()) {

            ProductGroup productGroup = productGroupMysqlDao.findEntityById(id, transaction);
            if (productGroup.isDisabled()) {
                productGroup.setDisabled(false);
                productGroupMysqlDao.update(productGroup, transaction);
                transaction.commit();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    private void buildProductGroup(ProductGroup productGroup, Transaction transaction) throws NullServiceException {
        try {
            List<Product> products = productGroup.getProducts();
            List<Product> allByProductGroupId = productMysqlDao.findAllByProductGroupId(productGroup.getId(), transaction);
            products.addAll(allByProductGroupId);
            log.info("buildProductGroup: products = {}", products);
        } catch (NullParamDaoException e) {
            throw new NullServiceException();
        }
    }

    /**
     * @return count of {@link ProductGroup} in the database
     * @throws ServiceException if service can't connect to the database
     */
    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return productGroupMysqlDao.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
