package by.epam.learn.task2.branching.example2.ex11.controller;

import by.epam.learn.task2.branching.example2.ex11.creator.TriangleCreator;
import by.epam.learn.task2.branching.example2.ex11.domain.Triangle;
import by.epam.learn.task2.branching.example2.ex11.exception.WrongSidesTriangleException;

public class Runner {


    public static void main(String[] args) {
        TriangleCreator triangleCreator = new TriangleCreator();
        try {
            Triangle triangle_fst =  triangleCreator.create(3, 4, 5);
            Triangle triangle_sec = triangleCreator.create(4, 5, 6);
            System.out.println(triangle_fst.compareTo(triangle_sec));
            System.out.println(triangle_fst.compareTo(triangle_fst));
        } catch (WrongSidesTriangleException e) {
            e.printStackTrace();
        }
    }
}
