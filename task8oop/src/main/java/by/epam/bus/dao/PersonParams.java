package by.epam.bus.dao;

public enum PersonParams {
    NAME(0, "Имя"),
    SURNAME(1, "Фамилия"),
    FATHER_NAME(2, "Отчество");

    private int number;
    private String title;

    PersonParams(int number, String title) {
        this.number = number;
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }
}
