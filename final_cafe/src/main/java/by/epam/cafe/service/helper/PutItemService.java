package by.epam.cafe.service.helper;

import by.epam.cafe.entity.db.impl.Product;

import java.util.Map;

/**
 * Dedicated to put Product in the basket
 * (Map<Product, Integer>)
 */
public interface PutItemService {
    /**
     * @param product  {@link Product} to put
     * @param products a basket in what should be put the product
     */
    void putProduct(final Product product, final Map<Product, Integer> products);
}
