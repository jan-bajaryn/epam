package by.epam.bus.dao;

public enum BusParams {
    BUS_NUMBER(3, "Номер автобуса"),
    TRACK_NUMBER(4, "Номер трассы"),
    STAMP(5, "Марка"),
    BEGIN_YEAR(6, "Дата начала эксплуатации"),
    MILLAGE(7, "Пробег");

    private int number;
    private String title;

    BusParams(int number, String title) {
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
