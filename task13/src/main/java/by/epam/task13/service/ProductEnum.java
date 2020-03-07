package by.epam.task13.service;

public enum ProductEnum {
    STUDENTS("students"),
    LOGIN("login"),
    FACULTY("faculty"),
    STUDENT("student"),
    NAME("name"),
    TELEPHONE("telephone"),
    COUNTRY("country"),
    CITY("city"),
    STREET("street"),
    ADDRESS("address");
    private String value;
    private ProductEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
