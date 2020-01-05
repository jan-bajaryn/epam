package by.epam.task5.ex2.example6.service;

import by.epam.task5.ex2.example6.entity.Pair;
import by.epam.task5.ex2.example6.service.exception.ArrayEmptyException;
import by.epam.task5.ex2.example6.service.exception.ArrayNullException;

public class NumericAxisService {
    public Pair findMinMax(int[] arr) throws ArrayNullException, ArrayEmptyException {

        if (arr == null) {
            throw new ArrayNullException();
        }
        if (arr.length == 0) {
            throw new ArrayEmptyException();
        }


        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return new Pair(min, max);
    }
}
