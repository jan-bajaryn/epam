package by.epam.cafe.service.db.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.DaoException;
import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.dao.mysql.Transaction;
import by.epam.cafe.dao.mysql.impl.ProductGroupMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.helper.ImageWriterService;
import by.epam.cafe.service.db.ProductGroupService;
import by.epam.cafe.service.exception.NullServiceException;
import by.epam.cafe.service.exception.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

import static by.epam.cafe.config.Configuration.MAX_PAGINATION_ELEMENTS;

public class ProductGroupServiceImpl implements ProductGroupService {

    private static final DiskFileItemFactory FILE_ITEM_FACTORY = new DiskFileItemFactory();


    private static final Logger log = LogManager.getLogger(ProductGroupServiceImpl.class);


    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductMysqlDao productMysqlDao = dAOFactory.getProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();

    private final ImageWriterService imageWriterService = new ImageWriterService();

    @Override
    public List<ProductGroup> findAll() throws NullParamDaoException, ServiceException {

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

    @Override
    public List<ProductGroup> findAllByPart(int part) throws NullParamDaoException, ServiceException {

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

    @Override
    public ProductGroup findEntityById(Integer integer) throws NullParamDaoException, ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            ProductGroup entityById = productGroupMysqlDao.findEntityById(integer, transaction);
            buildProductGroup(entityById, transaction);
            return entityById;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

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

    private void insertProductsIfPossible(ProductGroup entity, Transaction transaction) throws ServiceException {

        List<Product> products = entity.getProducts();
        if (products != null) {
            products.stream()
                    .map(p -> productMysqlDao.findEntityById(p.getId(), transaction))
                    .peek(p -> p.setProductGroup(entity))
                    .forEach(ent -> productMysqlDao.update(ent, transaction));
        }


    }

    @Override
    public boolean update(ProductGroup entity) throws ServiceException {

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
            deleteAll(toDelete,transaction);
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

    private void deleteAll(List<Product> toDelete, Transaction transaction) throws ServiceException {
        for (Product product : toDelete) {
            Product entityById = productMysqlDao.findEntityById(product.getId(), transaction);
            entityById.setProductGroup(null);
            productMysqlDao.update(entityById, transaction);
        }
    }

    private void insertAll(List<Product> toAdd, ProductGroup entity, Transaction transaction) throws ServiceException {
        for (Product product : toAdd) {
            Product entityById = productMysqlDao.findEntityById(product.getId(), transaction);
            entityById.setProductGroup(entity);
            productMysqlDao.update(entityById, transaction);
        }

    }

    @Override
    public List<ProductGroup> findAllByProductTypeNotDisabled(ProductType type) throws NullServiceException, ServiceException {

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

    @Override
    public List<ProductGroup> findAllExcept(ProductGroup productGroup) throws NullParamDaoException, ServiceException {
        List<ProductGroup> all = findAll();
        if (productGroup != null) {
            all = all.stream()
                    .filter(p -> !p.getId().equals(productGroup.getId()))
                    .collect(Collectors.toList());
        }
        return all;
    }


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

    private void buildProductGroup(ProductGroup productGroup, Transaction transaction) throws NullParamDaoException {
        List<Product> products = productGroup.getProducts();
        List<Product> allByProductGroupId = productMysqlDao.findAllByProductGroupId(productGroup.getId(), transaction);
        products.addAll(allByProductGroupId);
        log.info("buildProductGroup: products = {}", products);
    }


    private void log(FileItem part) {
        if (part.isFormField()) {
            log.debug("--------------------------");
            log.debug("part.getName() = {}", part.getName());
            log.debug("part.getContentType() = {}", part.getContentType());
            log.debug("part.getFieldName() = {}", part.getFieldName());
            log.debug("part = {}", part);
            log.debug("part.getString() = {}", part.getString());
        } else {
            log.debug("===============================");
            log.debug("part.getName() = {}", part.getName());
            log.debug("part.getContentType() = {}", part.getContentType());
            log.debug("part.getFieldName() = {}", part.getFieldName());
            log.debug("part = {}", part);
        }

    }

    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return productGroupMysqlDao.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
