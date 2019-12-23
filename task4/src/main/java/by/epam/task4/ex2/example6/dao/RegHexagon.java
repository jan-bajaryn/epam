package by.epam.task4.ex2.example6.dao;

public class RegHexagon implements RegPolygon {
    private double side;

    public RegHexagon(double side) {
        this.side = side;
    }

    @Override
    public double getSide() {
        return side;
    }

    @Override
    public int getNumberSide() {
        return 6;
    }
}
