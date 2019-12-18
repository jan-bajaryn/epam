package by.epam.task4.ex2.example6.domain;

public interface RegPolygon {
    double getSide();

    int getNumberSide();

    default double square() {
        return (((getNumberSide() / (double) 4) * (getSide() * 2)) * (1 / Math.tan(Math.PI / getNumberSide())));
    }
}
