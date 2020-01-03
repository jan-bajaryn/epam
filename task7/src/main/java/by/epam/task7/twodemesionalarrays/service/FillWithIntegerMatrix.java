package by.epam.task7.twodemesionalarrays.service;

import by.epam.task7.twodemesionalarrays.entity.Matrix;

public class FillWithIntegerMatrix {

    public void fillWithInteger(Matrix matrix, int value) {

        for (int i = 0; i < matrix.calcRows(); i++) {
            for (int j = 0; j < matrix.calcColumns(); j++) {
                matrix.setElement(i, j, value);
            }
        }
    }
}
