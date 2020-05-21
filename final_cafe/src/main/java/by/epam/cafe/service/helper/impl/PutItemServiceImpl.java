package by.epam.cafe.service.helper.impl;

import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.service.helper.PutItemService;

import java.util.Map;
import java.util.Optional;

public class PutItemServiceImpl implements PutItemService {


    /**
     * @param product  {@link Product} to put
     * @param products a basket in what should be put the product
     */
    @Override
    public void putProduct(Product product, Map<Product, Integer> products) {
        Optional<Product> any = products.keySet().stream()
                .filter(s -> s.getId().equals(product.getId()))
                .findAny();
        if (any.isPresent()) {
            Product prod = any.get();
            products.put(prod, products.get(prod) + 1);
        } else {
            products.put(product, 1);
        }
    }
}
