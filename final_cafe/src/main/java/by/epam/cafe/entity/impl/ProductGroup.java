package by.epam.cafe.entity.impl;

import by.epam.cafe.entity.Entity;
import by.epam.cafe.entity.enums.ProductType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductGroup extends Entity<Integer> implements Serializable {

    private String name;

    private String description;

    private String photoName;

    private ProductType type;

    private boolean disabled;

    private List<Product> products = new ArrayList<>();

    public ProductGroup() {
    }

    public ProductGroup(Integer integer, String name, String description, String photoName, ProductType type, boolean disabled, List<Product> products) {
        super(integer);
        this.name = name;
        this.description = description;
        this.photoName = photoName;
        this.type = type;
        this.disabled = disabled;
        this.products = products;
    }

    private ProductGroup(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setPhotoName(builder.photoName);
        setType(builder.type);
        setDisabled(builder.disabled);
        setProducts(builder.products);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductGroup that = (ProductGroup) o;

        if (disabled != that.disabled) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (photoName != null ? !photoName.equals(that.photoName) : that.photoName != null) return false;
        if (type != that.type) return false;
        return products != null ? products.equals(that.products) : that.products == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (disabled ? 1 : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public static final class Builder {
        private Integer id;
        private String name;
        private String description;
        private String photoName;
        private ProductType type;
        private boolean disabled;
        private List<Product> products = new ArrayList<>();

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder photoName(String val) {
            photoName = val;
            return this;
        }

        public Builder type(ProductType val) {
            type = val;
            return this;
        }

        public Builder disabled(boolean val) {
            disabled = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public ProductGroup build() {
            return new ProductGroup(this);
        }
    }
}
