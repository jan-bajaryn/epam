package by.epam.cafe.service.impl;

import by.epam.cafe.dao.DAOFactory;
import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.dao.mysql.impl.ProductGroupMysqlDao;
import by.epam.cafe.dao.mysql.impl.ProductMysqlDao;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.exception.NullServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ProductGroupServiceImpl implements by.epam.cafe.service.ProductGroupService {

    private static final DiskFileItemFactory FILE_ITEM_FACTORY = new DiskFileItemFactory();


    private static final Logger log = LogManager.getLogger(ProductGroupServiceImpl.class);


    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductMysqlDao productMysqlDao = dAOFactory.getProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();

    private final ImageWriterService imageWriterService = new ImageWriterService();

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
    public ProductGroup create(ProductGroup entity) {
        ProductGroup productGroup = productGroupMysqlDao.create(entity);
        insertProductsIfPossible(productGroup);
        return productGroup;
    }

    private void insertProductsIfPossible(ProductGroup entity) {
        List<Product> products = entity.getProducts();
        if (products != null) {
            products.stream()
                    .map(p -> productMysqlDao.findEntityById(p.getId()))
                    .peek(p -> p.setProductGroup(entity))
                    .forEach(productMysqlDao::update);
        }
    }

    @Override
    public boolean update(ProductGroup entity) throws NullParamDaoException {
        boolean update = productGroupMysqlDao.update(entity);
        insertAndDeleteOthers(entity);
        return update;
    }

    private void insertAndDeleteOthers(ProductGroup entity) throws NullParamDaoException {
        final List<Product> products = entity.getProducts();
        final List<Product> allByProductGroupId = productMysqlDao.findAllByProductGroupId(entity.getId());

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
        deleteAll(toDelete);
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
        insertAll(toAdd, entity);
    }

    private void deleteAll(List<Product> toDelete) {
        for (Product product : toDelete) {
            Product entityById = productMysqlDao.findEntityById(product.getId());
            entityById.setProductGroup(null);
            productMysqlDao.update(entityById);
        }
    }

    // TODO check if there more efficient way to do that
    private void insertAll(List<Product> toAdd, ProductGroup entity) {
        for (Product product : toAdd) {
            Product entityById = productMysqlDao.findEntityById(product.getId());
            entityById.setProductGroup(entity);
            productMysqlDao.update(entityById);
        }
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


    // TODO List<FileItem> instead of request
    @Override
    public ProductGroup parseRequest(HttpServletRequest request) {
        try {

            ProductGroup productGroup = new ProductGroup();

            ServletFileUpload fileUpload = new ServletFileUpload(FILE_ITEM_FACTORY);
            List<FileItem> parts = fileUpload.parseRequest(request);

            for (FileItem part : parts) {
                log(part);

                fillFields(productGroup, part);
            }
            return productGroup;
        } catch (FileUploadException e) {
            log.error("e: ", e);
            return null;
        }
    }

    @Override
    public void disableById(Integer id) {
        ProductGroup productGroup = productGroupMysqlDao.findEntityById(id);
        if (!productGroup.isDisabled()) {
            productGroup.setDisabled(true);
            productGroupMysqlDao.update(productGroup);
        }
    }

    @Override
    public void enableById(Integer id) {
        ProductGroup productGroup = productGroupMysqlDao.findEntityById(id);
        if (productGroup.isDisabled()) {
            productGroup.setDisabled(false);
            productGroupMysqlDao.update(productGroup);
        }
    }

    private void fillFields(ProductGroup productGroup, FileItem part) {

        File file = null;

        try {
            switch (part.getFieldName()) {
                case "name":
                    productGroup.setName(part.getString());
                    break;
                case "type":
                    productGroup.setType(ProductType.valueOf(part.getString()));
                    break;
                case "description":
                    productGroup.setDescription(part.getString());
                    break;
                case "products":
                    productGroup.getProducts().add(
                            Product.newBuilder().id(Integer.valueOf(part.getString())).build()
                    );
                    break;
                case "file":
                    file = imageWriterService.downloadFile(part);
                    productGroup.setPhotoName(file.getName());
                    break;
                case "id":
                    productGroup.setId(Integer.valueOf(part.getString()));
                    break;
                case "disabled":
                    // TODO check that
                    productGroup.setDisabled(part.getString().equals("1"));
                    break;
                default:
                    log.error("irregular field");
            }
        } catch (Exception e) {
            if (file != null) {
                // TODO check security
                file.delete();
            }
        }

    }

    private void buildProductGroup(ProductGroup productGroup) throws NullParamDaoException {
        List<Product> products = productGroup.getProducts();
        List<Product> allByProductGroupId = productMysqlDao.findAllByProductGroupId(productGroup.getId());
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
}
