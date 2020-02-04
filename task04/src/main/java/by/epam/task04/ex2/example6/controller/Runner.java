package by.epam.task04.ex2.example6.controller;

public class Runner {
    public static void main(String[] args) {
        RegShapeSquareCommand regShapeSquareCommand = new RegShapeSquareCommand();

        regShapeSquareCommand.execute(4.3);
        regShapeSquareCommand.execute(0);
        regShapeSquareCommand.execute(-1);
    }
}
