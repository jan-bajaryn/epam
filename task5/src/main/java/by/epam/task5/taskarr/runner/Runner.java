package by.epam.task5.taskarr.runner;

import by.epam.task5.taskarr.controller.ArrayFiller;
import by.epam.task5.taskarr.controller.ArrayReverser;
import by.epam.task5.taskarr.controller.ReverseArrayCommand;
import by.epam.task5.taskarr.controller.ReverseArrayRandomly;
import by.epam.task5.taskarr.exception.IllegalArgumentSizeException;
import by.epam.task5.taskarr.exception.IllegalInputParameterException;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        ReverseArrayCommand reverseArrayCommand = new ReverseArrayCommand();
        ReverseArrayRandomly reverseArrayRandomly = new ReverseArrayRandomly();
        int[] arr;

        arr = new int[]{};
        reverseArrayCommand.execute(arr);
        arr = new int[]{1};
        reverseArrayCommand.execute(arr);
        arr = new int[]{1, 2};
        reverseArrayCommand.execute(arr);
        arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        reverseArrayCommand.execute(arr);

        reverseArrayRandomly.execute(10, 3, 100);
    }

}
