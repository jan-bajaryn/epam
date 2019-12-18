package by.epam.task4.ex1.creator;

import by.epam.task4.ex1.domain.Point;
import by.epam.task4.ex1.domain.Triangle;
import by.epam.task4.ex1.exception.IllegalPointsTriangleException;
import by.epam.task4.ex1.validator.TriangleValidator;

public class TriangleCreator {
    TriangleValidator triangleValidator = new TriangleValidator();

    public Triangle create(Point f, Point s, Point t) throws IllegalPointsTriangleException {
        Triangle triangle = new Triangle(f, s, t);
        if (triangleValidator.isValid(triangle)) {
            return triangle;
        } else {
            throw new IllegalPointsTriangleException();
        }
    }
}
