package by.epam.task4.ex2.example6.controller;

import by.epam.task4.ex2.example6.creator.RegHexagonCreator;
import by.epam.task4.ex2.example6.domain.RegHexagon;
import by.epam.task4.ex2.example6.domain.RegPolygon;
import by.epam.task4.ex2.example6.exception.InvalidRegHexagonException;

public class RegShapeSquareCommand {

    private RegHexagonCreator regHexagonCreator;

    public RegShapeSquareCommand() {
        regHexagonCreator = new RegHexagonCreator();
    }

    public void execute(double side) {
        try {
            RegPolygon regHexagon = regHexagonCreator.create(side);
            System.out.println("Square of hexagon = " + regHexagon.square());
        } catch (InvalidRegHexagonException e) {
            System.out.println("Side can't be less than or equal 0");
        }
    }
}
