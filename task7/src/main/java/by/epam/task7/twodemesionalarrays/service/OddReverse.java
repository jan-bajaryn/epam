package by.epam.task7.exercise2.ex11.service;

import by.epam.task7.twodemesionalarrays.entity.Matrix;

public class OddReverse {
    public void reverse(Matrix matrix) {
        int columns = matrix.calcColumns();
        int rows = matrix.calcRows();
        for (int i = 0; i < rows; i += 2) {
            int temp = columns - 1;
            int[] arr = new int[columns];

            for (int j = 0; j < columns; j++) {
                arr[temp--] = matrix.calcElem(i, j);
            }

            for (int j = 0; j < columns; j++) {
                matrix.setElement(i, j, arr[j]);
            }

        }
    }
}
