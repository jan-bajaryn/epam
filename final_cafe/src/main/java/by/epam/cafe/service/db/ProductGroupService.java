package by.epam.cafe.service.db;

import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.service.exception.NullServiceException;
import by.epam.cafe.service.exception.ServiceException;

import java.util.List;

public interface ProductGroupService {

    List<ProductGroup> findAll() throws NullParamDaoException, ServiceException;
    List<ProductGroup> findAllByPart(int begin) throws NullParamDaoException, ServiceException;

    ProductGroup findEntityById(Integer integer) throws NullParamDaoException, ServiceException;

    ProductGroup create(ProductGroup entity) throws ServiceException;

    boolean update(ProductGroup entity) throws ServiceException;

    List<ProductGroup> findAllByProductTypeNotDisabled(ProductType type) throws NullServiceException, ServiceException;

    List<ProductGroup> findAllExcept(ProductGroup productGroup) throws NullParamDaoException, ServiceException;

    void disableById(Integer id) throws ServiceException;

    void enableById(Integer id) throws ServiceException;

    int count() throws ServiceException;

}
