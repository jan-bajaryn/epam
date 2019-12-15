package by.epam.learn.task1.linear.example2.validator;

import by.epam.learn.task1.linear.example2.domain.RectTriangle;

public class RectTriangleValidator {
    public boolean isValid(RectTriangle rectTriangle) {
        return rectTriangle.getCathet1() > 0 && rectTriangle.getCathet2() > 0;
    }
}
