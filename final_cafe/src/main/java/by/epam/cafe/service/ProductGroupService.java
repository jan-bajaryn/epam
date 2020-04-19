package by.epam.cafe.service;

import by.epam.cafe.dao.exception.NullParamDaoException;
import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.exception.NullServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductGroupService {

    List<ProductGroup> findAll() throws NullParamDaoException;

    ProductGroup findEntityById(Integer integer) throws NullParamDaoException;

    boolean deleteById(Integer integer);

    boolean delete(ProductGroup entity);

    ProductGroup create(ProductGroup entity);

    boolean update(ProductGroup entity);

    List<ProductGroup> findAllByProductTypeNotDisabled(ProductType type) throws NullServiceException;

    List<ProductGroup> findAllEmpty();

    List<ProductGroup> findAllExcept(ProductGroup productGroup) throws NullParamDaoException;

    ProductGroup parseRequest(HttpServletRequest request);

}
