package by.epam.cafe.entity.impl;


import by.epam.cafe.entity.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product extends Entity<Integer> implements Serializable {

    private Integer price;

    private Integer weight;

    private ProductGroup productGroup;

    private List<Order> orders = new ArrayList<>();

    public Product() {
    }

    public Product(Integer integer, Integer price, Integer weight, ProductGroup productGroup, List<Order> orders) {
        super(integer);
        this.price = price;
        this.weight = weight;
        this.productGroup = productGroup;
        this.orders = orders;
    }

    private Product(Builder builder) {
        setId(builder.id);
        price = builder.price;
        weight = builder.weight;
        productGroup = builder.productGroup;
        orders = builder.orders;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (weight != null ? !weight.equals(product.weight) : product.weight != null) return false;
        if (productGroup != null ? !productGroup.equals(product.productGroup) : product.productGroup != null)
            return false;
        return orders != null ? orders.equals(product.orders) : product.orders == null;
    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (productGroup != null ? productGroup.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    public static final class Builder {
        private Integer id;
        private Integer price;
        private Integer weight;
        private ProductGroup productGroup;
        private List<Order> orders;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder price(Integer val) {
            price = val;
            return this;
        }

        public Builder weight(Integer val) {
            weight = val;
            return this;
        }

        public Builder productGroup(ProductGroup val) {
            productGroup = val;
            return this;
        }

        public Builder orders(List<Order> val) {
            orders = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
