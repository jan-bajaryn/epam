package by.epam.cafe.service;

import by.epam.cafe.entity.db.impl.Product;

import java.util.Map;

public interface PutItemService {
    void putProduct(final Product product, final Map<Product, Integer> products);
}
