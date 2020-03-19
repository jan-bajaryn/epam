package by.epam.cafe.entity.impl;

import by.epam.cafe.entity.Entity;
import by.epam.cafe.entity.enums.ProductType;

public class Product extends Entity<Long> {
    private String name;
    private String description;
    private String photoName;
    private Integer price;
    private ProductType productType;
    private Integer productSize;

    public Product() {
    }

    public Product(Long id, String name, String description, String photoName, Integer price, ProductType productType, Integer productSize) {
        super(id);
        this.name = name;
        this.description = description;
        this.photoName = photoName;
        this.price = price;
        this.productType = productType;
        this.productSize = productSize;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Integer getProductSize() {
        return productSize;
    }

    public void setProductSize(Integer productSize) {
        this.productSize = productSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (getId() != null ? !getId().equals(product.getId()) : product.getId() != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (photoName != null ? !photoName.equals(product.photoName) : product.photoName != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (productType != null ? !productType.equals(product.productType) : product.productType != null) return false;
        return productSize != null ? productSize.equals(product.productSize) : product.productSize == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (productSize != null ? productSize.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photoName='" + photoName + '\'' +
                ", price=" + price +
                ", productType=" + productType +
                ", productSize=" + productSize +
                '}';
    }
}
