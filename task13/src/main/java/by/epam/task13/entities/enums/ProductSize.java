package by.epam.task13.entities.enums;

public enum ProductSize {
    SM("sm"), MD("md"), LG("lg");

    private String value;

    ProductSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
