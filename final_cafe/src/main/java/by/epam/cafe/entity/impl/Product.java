package by.epam.cafe.entity.impl;


import by.epam.cafe.entity.Entity;

import java.io.Serializable;

public class Product extends Entity<Integer> implements Serializable {

    private Integer price;

    private Integer weight;

    private ProductGroup productGroup;


    public Product() {
    }

    public Product(Integer integer, Integer price, Integer weight, ProductGroup productGroup) {
        super(integer);
        this.price = price;
        this.weight = weight;
        this.productGroup = productGroup;
    }

    private Product(Builder builder) {
        setId(builder.id);
        setPrice(builder.price);
        setWeight(builder.weight);
        setProductGroup(builder.productGroup);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (weight != null ? !weight.equals(product.weight) : product.weight != null) return false;
        return productGroup != null ? productGroup.equals(product.productGroup) : product.productGroup == null;
    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (productGroup != null ? productGroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", weight=" + weight +
                ", productGroup=" + productGroup +
                '}';
    }

    public static final class Builder {
        private Integer id;
        private Integer price;
        private Integer weight;
        private ProductGroup productGroup;

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

        public Product build() {
            return new Product(this);
        }
    }
}
