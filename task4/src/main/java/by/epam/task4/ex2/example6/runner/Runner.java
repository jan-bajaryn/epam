package by.epam.task4.ex2.example6.runner;

import by.epam.task4.ex2.example6.controller.RegShapeSquareCommand;

public class Runner {
    public static void main(String[] args) {
        RegShapeSquareCommand regShapeSquareCommand = new RegShapeSquareCommand();

        regShapeSquareCommand.execute(4.3);
    }
}
