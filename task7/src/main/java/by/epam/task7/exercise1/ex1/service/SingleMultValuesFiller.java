package by.epam.task7.exercise1.ex1.service;

import by.epam.task7.twodemesionalarrays.entity.Matrix;

public class SingleMultValuesFiller {
    public void fill(Matrix matrix, int valueSingle, int valueMultiple) throws NullMatrixException {
        if (matrix == null) {
            throw new NullMatrixException();
        }

        for (int i = 0; i < matrix.calcRows(); i++) {
            matrix.setElement(i, 0, valueSingle);
            for (int j = 1; j < matrix.calcColumns(); j++) {
                matrix.setElement(i, j, valueMultiple);
            }
        }
    }
}
