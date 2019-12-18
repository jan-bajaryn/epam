package by.epam.taskarr.controller;

import by.epam.taskarr.exception.IllegalArgumentSizeException;

import java.util.Random;

public class ArrayFill {
    public int[] fillRandomly(int size, int from, int to) throws IllegalArgumentSizeException {
        if (size < 0)
            throw new IllegalArgumentSizeException();

        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt((to - from) + 1) - from;
        }
        return arr;
    }
}
