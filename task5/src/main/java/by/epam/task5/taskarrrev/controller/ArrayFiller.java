package by.epam.task5.taskarrrev.controller;


import by.epam.task5.taskarrrev.exception.IllegalArgumentSizeException;
import by.epam.task5.taskarrrev.exception.IllegalInputParameterException;

import java.util.Random;

public class ArrayFiller {
    public int[] fillRandomly(int size, int from, int to) throws IllegalArgumentSizeException, IllegalInputParameterException {
        if (size < 0)
            throw new IllegalArgumentSizeException();

        if (from>to)
            throw new IllegalInputParameterException();
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt((to - from) + 1) + from;
        }
        return arr;
    }
}
