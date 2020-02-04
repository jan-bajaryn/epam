package by.epam.task07.twodemesionalarrays.service;

import by.epam.task07.twodemesionalarrays.entity.Matrix;

public class MaxMinFinder {
    public int findMaximum(Matrix matrix) {
        int max = matrix.calcElem(0, 0);
        for (int i = 0; i < matrix.calcRows(); i++) {
            for (int j = 0; j < matrix.calcColumns(); j++) {
                int current = matrix.calcElem(i, j);
                if (max < current) {
                    max = current;
                }
            }
        }
//        System.out.println("max = " + max);
        return max;
    }

    public int findMinimum(Matrix matrix) {
        int min = matrix.calcElem(0, 0);
        for (int i = 0; i < matrix.calcRows(); i++) {
            for (int j = 0; j < matrix.calcColumns(); j++) {
                int current = matrix.calcElem(i, j);
                if (min > current) {
                    min = current;
                }
            }
        }
//        System.out.println("min = " + min);
        return min;
    }
}
