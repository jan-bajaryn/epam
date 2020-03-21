package by.epam.cafe.entity.impl;


import by.epam.cafe.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Product extends Entity<Integer> {

    private Integer price;

    private Integer weight;

    private ProductGroup productGroup;

    private List<Order> orders = new ArrayList<>();

}
