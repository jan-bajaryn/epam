package by.epam.task4.ex1.exapmle1.validator;

import by.epam.task4.ex1.exapmle1.entity.Triangle;

public class TriangleValidator {
    public boolean isValid(Triangle triangle) {
        return !(triangle.getF().equals(triangle.getS())
                || triangle.getF().equals(triangle.getT())
                || triangle.getS().equals(triangle.getF())
                || (
                        (triangle.getF().getX() == triangle.getS().getX() && triangle.getS().getX() == triangle.getT().getX())
                        || (triangle.getF().getY() == triangle.getS().getY() && triangle.getS().getY() == triangle.getT().getY())
                   )
        );
    }
}
