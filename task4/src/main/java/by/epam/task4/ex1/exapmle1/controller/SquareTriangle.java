package by.epam.task4.ex1.exapmle1.controller;

import by.epam.task4.ex1.exapmle1.domain.Triangle;

public class SquareTriangle {
    public double square(Triangle triangle) {
        double x1 = triangle.getF().getX();
        double x2 = triangle.getS().getX();
        double x3 = triangle.getT().getX();
        double y1 = triangle.getF().getY();
        double y2 = triangle.getS().getY();
        double y3 = triangle.getT().getY();
//        A	x 	 (	 B	y 	−	 C	y 	) 	+	 B	x 	 (	 C	y 	−	 A	y 	) 	+	 C	x 	 (	 A	y 	−	 B	y 	)
        return Math.abs(0.5 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)));
    }
}
