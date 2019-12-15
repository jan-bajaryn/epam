package by.epam.learn.task1.linear.example2.controller;

import by.epam.learn.task1.linear.example2.creator.RectTriangleCreator;
import by.epam.learn.task1.linear.example2.domain.RectTriangle;
import by.epam.learn.task1.linear.example2.exception.WrongCathetException;

public class Runner {

    public static void main(String[] args) {
        RectTriangleCreator rectTriangleCreator = new RectTriangleCreator();
        RectTriangle rectTriangle = null;
        try {
            rectTriangle = rectTriangleCreator.create(34, 45.23);
            System.out.println(rectTriangle.square());
            System.out.println(rectTriangle.perimeter());
        } catch (WrongCathetException e) {
            System.out.println("Cathet can't be less or equal 0");
        }

    }
}
