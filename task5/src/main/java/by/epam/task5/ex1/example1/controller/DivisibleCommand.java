package by.epam.task5.ex1.example1.controller;

import by.epam.task5.ex1.example1.service.exception.IllegalInputNaturalException;
import by.epam.task5.ex1.example1.service.DivisibleService;

import java.util.Arrays;

public class DivisibleCommand {
    private DivisibleService divisibleService;

    public DivisibleCommand() {
        divisibleService = new DivisibleService();
    }

    public void execute(int[] arr, int divider) {
        try {

            int sum = divisibleService.sumDivident(divider, arr);
            System.out.println("Array is: " + Arrays.toString(arr));
            System.out.println("Sum numbers what divided to " + divider + " is = " + sum);

        } catch (IllegalInputNaturalException e) {
            System.out.println("You can't put in array not natural numbers");
        }

    }
}
