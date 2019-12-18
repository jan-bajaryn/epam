package by.epam.task4.ex1.runner;

import by.epam.task4.ex1.controller.SquareTriangle;
import by.epam.task4.ex1.controller.SquareTriangleCommand;
import by.epam.task4.ex1.creator.PointCreator;
import by.epam.task4.ex1.creator.TriangleCreator;
import by.epam.task4.ex1.domain.Point;
import by.epam.task4.ex1.domain.Triangle;
import by.epam.task4.ex1.exception.IllegalPointsTriangleException;

public class Runner {
    public static void main(String[] args) throws IllegalPointsTriangleException {
        double x1 = 0;
        double x2 = 10;
        double x3 = 35;
        double y1 = 0;
        double y2 = 10;
        double y3 = 5;

        SquareTriangleCommand squareTriangleCommand = new SquareTriangleCommand();
        squareTriangleCommand.execute(x1, x2, x3, y1, y2, y3);

    }
}
