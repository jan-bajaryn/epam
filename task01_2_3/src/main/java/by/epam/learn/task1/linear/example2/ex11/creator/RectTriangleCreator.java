package by.epam.learn.task1.linear.example2.ex11.creator;

import by.epam.learn.task1.linear.example2.ex11.domain.RectTriangle;
import by.epam.learn.task1.linear.example2.ex11.creator.exception.WrongCathetException;
import by.epam.learn.task1.linear.example2.ex11.validator.RectTriangleValidator;

public class RectTriangleCreator {
    RectTriangleValidator rectTriangleValidator;

    public RectTriangleCreator() {
        rectTriangleValidator = new RectTriangleValidator();
    }

    public RectTriangle create(double cathet1, double cathet2) throws WrongCathetException {
        RectTriangle rectTriangle = new RectTriangle(cathet1, cathet2);
        if (rectTriangleValidator.isValid(rectTriangle)) {
            return rectTriangle;
        } else {
            throw new WrongCathetException();
        }
    }
}
