package by.epam.learn.task2.branching.example2.ex11.validator;

import by.epam.learn.task2.branching.example2.ex11.entity.Triangle;

public class TriangleValidator {
    public boolean isValid(Triangle triangle){
        return sidesOk(triangle.getFirstSide(), triangle.getSecondSide(), triangle.getThirdSide());
    }

    private boolean isMoreThanZero(double f, double s, double t) {
        return f > 0 || s > 0 || t > 0;
    }

    private boolean sumSidesOk(double f, double s, double t) {
        return f + s > t || f + t > s || s + t > f;
    }

    private boolean sidesOk(double f, double s, double t) {
        return isMoreThanZero(f, s, t) && sumSidesOk(f, s, t);
    }

}
