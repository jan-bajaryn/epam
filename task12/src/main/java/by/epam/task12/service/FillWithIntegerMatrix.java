package by.epam.task12.service;


import by.epam.task12.entity.impl.MatrixImpl;

public class FillWithIntegerMatrix {

    public void fillWithInteger(MatrixImpl matrixImpl, int value) {

        for (int i = 0; i < matrixImpl.calcRows(); i++) {
            for (int j = 0; j < matrixImpl.calcColumns(); j++) {
                matrixImpl.setElement(i, j, value);
            }
        }
    }

    public void fillWithInteger(MatrixImpl matrixImpl, int bRow, int bCol, int eRow, int eCol, int value) {
        for (int i = bRow; i <= eRow; i++) {
            for (int j = bCol; j <= eCol; j++) {
                matrixImpl.setElement(i, j, value);
            }
        }
    }
}
