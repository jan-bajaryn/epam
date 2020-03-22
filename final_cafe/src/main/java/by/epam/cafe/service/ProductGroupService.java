package by.epam.cafe.service;

import by.epam.cafe.entity.impl.ProductGroup;

import java.util.List;

public interface ProductGroupService {
    List<ProductGroup> findAll();

    ProductGroup findEntityById(Integer integer);

    boolean deleteById(Integer integer);

    boolean delete(ProductGroup entity);

    boolean create(ProductGroup entity);

    boolean update(ProductGroup entity);
}
