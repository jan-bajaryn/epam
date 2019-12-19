package by.epam.task5.ex2.example6.controller;

import by.epam.task5.ex2.example6.dao.Pair;
import by.epam.task5.ex2.example6.exception.ArrayEmptyException;
import by.epam.task5.ex2.example6.exception.ArrayNullException;
import by.epam.task5.ex2.example6.service.NumericAxisService;

public class NumericAxisCommand {
    private NumericAxisService numericAxisService;

    public NumericAxisCommand() {
        numericAxisService = new NumericAxisService();
    }

    public void execute(int[] arr) {
        try {
            Pair minMax = numericAxisService.findMinMax(arr);
            System.out.println("axis is from " + minMax.getF() + " to " + minMax.getS());
        } catch (ArrayNullException e) {
            System.out.println("Array can't be null");
        } catch (ArrayEmptyException e) {
            System.out.println("Array can't be empty");
        }
    }
}