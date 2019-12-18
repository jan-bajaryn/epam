package by.epam.task4.controller;

import by.epam.task4.domain.Triangle;

public class SquareTriangle {
    public double square(Triangle triangle) {
        // S=0,5*[(x1-x3)(y2-y3)-(x2-x3)(y1-y3)].
        double x1 = triangle.getF().getX();
        double x2 = triangle.getS().getX();
        double x3 = triangle.getT().getX();
        double y1 = triangle.getF().getY();
        double y2 = triangle.getS().getY();
        double y3 = triangle.getT().getY();
        return 0.5 * ((x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3));
    }
}
