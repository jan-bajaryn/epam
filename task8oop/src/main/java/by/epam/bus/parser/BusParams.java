package by.epam.bus.parser;

public enum BusParams {
    NAME(0),
    SURNAME(1),
    FATHER_NAME(2),
    BUS_NUMBER(3),
    TRACK_NUMBER(4),
    STAMP(5),
    BEGIN_YEAR(6),
    MILLAGE(7);

    private int number;

    BusParams(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
