package by.epam.task4.ex2.example6.controller;

import by.epam.task4.ex2.example6.creator.RegHexagonCreator;
import by.epam.task4.ex2.example6.entity.RegPolygon;
import by.epam.task4.ex2.example6.creator.exception.InvalidRegHexagonException;
import by.epam.task4.ex2.example6.service.RegShapeSquare;

public class RegShapeSquareCommand {

    private RegHexagonCreator regHexagonCreator;
    private RegShapeSquare regShapeSquare;

    public RegShapeSquareCommand() {
        regHexagonCreator = new RegHexagonCreator();
        regShapeSquare = new RegShapeSquare();
    }

    public void execute(double side) {
        try {
            RegPolygon regHexagon = regHexagonCreator.create(side);
            System.out.println("Square of hexagon = " + regShapeSquare.calculateSq(regHexagon));
        } catch (InvalidRegHexagonException e) {
            System.out.println("Side can't be less than or equal 0");
        }
    }
}
