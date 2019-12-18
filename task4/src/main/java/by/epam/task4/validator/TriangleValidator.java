package by.epam.task4.validator;

import by.epam.task4.domain.Triangle;

public class TriangleValidator {
    public boolean isValid(Triangle triangle) {
        return !(triangle.getF().equals(triangle.getS())
                && triangle.getF().equals(triangle.getT())
                && triangle.getS().equals(triangle.getF()));
    }
}
