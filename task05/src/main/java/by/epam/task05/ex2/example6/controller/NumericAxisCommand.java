package by.epam.task05.ex2.example6.controller;

import by.epam.task05.ex2.example6.entity.Pair;
import by.epam.task05.ex2.example6.service.exception.ArrayEmptyException;
import by.epam.task05.ex2.example6.service.exception.ArrayNullException;
import by.epam.task05.ex2.example6.service.NumericAxisService;

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
