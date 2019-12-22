package by.epam.task5.ex4.example16.service;

import by.epam.task5.ex4.example16.exception.ArrayEmptyException;
import by.epam.task5.ex4.example16.exception.ArrayNotEvenException;
import by.epam.task5.ex4.example16.exception.ArrayNullException;

public class FindMaxForm {
    public double find(double[] arr) throws ArrayNullException, ArrayEmptyException, ArrayNotEvenException {

        validateArray(arr);

        int bPos = 0;
        int lPos = arr.length - 1;

        double max = arr[bPos++] + arr[lPos--];

        while (bPos < lPos) {
            double temp = arr[bPos++] + arr[lPos--];
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    private void validateArray(double[] arr) throws ArrayNullException, ArrayEmptyException, ArrayNotEvenException {
        if (arr == null) {
            throw new ArrayNullException();
        }
        if (arr.length == 0) {
            throw new ArrayEmptyException();
        }
        if (arr.length % 2 != 0) {
            throw new ArrayNotEvenException();
        }
    }

}
