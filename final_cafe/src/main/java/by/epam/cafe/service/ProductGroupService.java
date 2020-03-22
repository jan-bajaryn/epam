package by.epam.cafe.service;

import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.entity.impl.ProductGroup;
import by.epam.cafe.service.exception.NullServiceException;

import java.util.List;

public interface ProductGroupService {
    List<ProductGroup> findAll();

    ProductGroup findEntityById(Integer integer);

    boolean deleteById(Integer integer);

    boolean delete(ProductGroup entity);

    boolean create(ProductGroup entity);

    boolean update(ProductGroup entity);

    List<ProductGroup> findAllByProductTypeNotDisabled(ProductType type) throws NullServiceException;
}
