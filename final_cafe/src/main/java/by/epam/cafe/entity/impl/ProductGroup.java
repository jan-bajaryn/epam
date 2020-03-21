package by.epam.cafe.entity.impl;

import by.epam.cafe.entity.Entity;
import by.epam.cafe.entity.enums.ProductType;

import java.util.Set;

public class ProductGroup extends Entity<Integer> {

    private String name;

    private String description;

    private String photoName;

    private ProductType type;

    private Set<Product> products;

    private boolean disabled;
}
