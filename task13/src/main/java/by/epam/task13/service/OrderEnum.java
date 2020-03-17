package by.epam.task13.service;

public enum OrderEnum {

    ID("id"),

    ORDERS("orders"),
    PRODUCTS("products"),
    INGREDIENTS("ingredients"),
    DELIVERY_INF("delivery_inf"),

    ORDER("order"),
    PRODUCT("product"),
    STATUS("status"),
    NAME("name"),
    DESCRIPTION("description"),
    PHOTO_NAME("photo_name"),
    PRICE("price"),
    PRODUCT_TYPE("product_type"),
    PRODUCT_SIZE("product_size"),
    INGREDIENT("ingredient"),
    CREATION("creation"),
    TOTAL_PRICE("total_price"),
    PAYMENT_TYPE("payment_type"),
    DELIVERY_TIME("delivery_time"),
    CLIENT_NAME("client_name"),
    ADDRESS("address"),
    PHONE("phone"),
    EMAIL("email");

    private String value;

    OrderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
