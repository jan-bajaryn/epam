package by.epam.task13.entities;

import by.epam.task13.entities.enums.ProductType;

public class Product {
    private String name;
    private String description;
    private String photoName;
    private String price;
    private ProductType type;

    public Product(String name, String description, String photoName, String price, ProductType type) {
        this.name = name;
        this.description = description;
        this.photoName = photoName;
        this.price = price;
        this.type = type;
    }

    public Product() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (photoName != null ? !photoName.equals(product.photoName) : product.photoName != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        return type == product.type;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photoName='" + photoName + '\'' +
                ", price='" + price + '\'' +
                ", type=" + type +
                '}';
    }
}
