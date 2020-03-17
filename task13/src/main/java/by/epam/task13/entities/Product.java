package by.epam.task13.entities;

import by.epam.task13.entities.enums.ProductSize;
import by.epam.task13.entities.enums.ProductType;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private String description;
    private String photoName;
    private Integer price;
    private ProductType type;
    private ProductSize size;
    private List<String> ingredients;

    public Product(String name, String description, String photoName, Integer price, ProductType type, ProductSize size, List<String> ingredients) {
        this.name = name;
        this.description = description;
        this.photoName = photoName;
        this.price = price;
        this.type = type;
        this.size = size;
        this.ingredients = ingredients;
    }

    public static Product.Builder builder() {
        return new Product.Builder();
    }

    public Product() {
    }

    private Product(Builder builder) {
        setName(builder.name);
        setDescription(builder.description);
        setPhotoName(builder.photoName);
        setPrice(builder.price);
        setType(builder.type);
        setSize(builder.size);
        setIngredients(builder.ingredients);
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

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ProductSize getSize() {
        return size;
    }

    public void setSize(ProductSize size) {
        this.size = size;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
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
        if (type != product.type) return false;
        if (size != product.size) return false;
        return ingredients != null ? ingredients.equals(product.ingredients) : product.ingredients == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photoName='" + photoName + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", size=" + size +
                ", ingredients=" + ingredients +
                '}';
    }

    public static final class Builder {
        private String name;
        private String description;
        private String photoName;
        private Integer price;
        private ProductType type;
        private ProductSize size;
        private List<String> ingredients;

        public Builder() {
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

        public Builder price(Integer val) {
            price = val;
            return this;
        }

        public Builder type(ProductType val) {
            type = val;
            return this;
        }

        public Builder size(ProductSize val) {
            size = val;
            return this;
        }

        public Builder ingredients(List<String> val) {
            ingredients = val;
            return this;
        }

        public Product build() {
            if (ingredients == null) {
                ingredients = new ArrayList<>();
            }
            return new Product(this);
        }
    }
}

