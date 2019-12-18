package by.epam.task4.ex1.controller;

import by.epam.task4.ex1.creator.PointCreator;
import by.epam.task4.ex1.creator.TriangleCreator;
import by.epam.task4.ex1.domain.Point;
import by.epam.task4.ex1.domain.Triangle;
import by.epam.task4.ex1.exception.IllegalPointsTriangleException;
import by.epam.task4.ex1.validator.TriangleValidator;

public class SquareTriangleCommand {
    SquareTriangle squareTriangle;
    PointCreator pointCreator;
    TriangleCreator triangleCreator;

    public SquareTriangleCommand() {
        squareTriangle = new SquareTriangle();
        pointCreator = new PointCreator();
        triangleCreator = new TriangleCreator();
    }

    public void execute(double x1,
                        double x2,
                        double x3,
                        double y1,
                        double y2,
                        double y3) {

        Point pointF = pointCreator.create(x1, y1);
        Point pointS = pointCreator.create(x2, y2);
        Point pointT = pointCreator.create(x3, y3);

        try {
            Triangle triangle = triangleCreator.create(pointF, pointS, pointT);

            System.out.println("Square of this triangle is = " + squareTriangle.square(triangle));

        } catch (IllegalPointsTriangleException e) {
            System.out.println("Your points input is incorrect.");
        }

    }
}
