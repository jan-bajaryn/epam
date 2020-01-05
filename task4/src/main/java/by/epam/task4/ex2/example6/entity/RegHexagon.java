package by.epam.task4.ex2.example6.entity;

public class RegHexagon implements RegPolygon {
    private double side;

    public RegHexagon(double side) {
        this.side = side;
    }

    @Override
    public double calcSide() {
        return side;
    }

    @Override
    public int calcSideNumber() {
        return 6;
    }
}
