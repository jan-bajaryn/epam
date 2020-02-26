package by.epam.task12.entity.factory.impl;

import by.epam.task12.entity.factory.MatrixFactory;
import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.entity.impl.MatrixAtomicImpl;
import by.epam.task12.service.validator.MatrixValidator;

import java.util.concurrent.atomic.AtomicInteger;

public class MatrixAtomicImplFactory implements MatrixFactory<MatrixAtomicImpl> {
    MatrixValidator matrixValidator = new MatrixValidator();

    @Override
    public MatrixAtomicImpl create(int rows, int columns) throws IllegalArgsMatrixException {
        if (!matrixValidator.validateMatrixArgs(rows, columns)) {
            throw new IllegalArgsMatrixException();
        }

        MatrixAtomicImpl matrixElements = new MatrixAtomicImpl(rows, columns);
        initMatrix(rows, columns, matrixElements);
        return matrixElements;
    }

    @Override
    public MatrixAtomicImpl create(int[][] arr) throws IllegalArgsMatrixException {
        if (!matrixValidator.isValidArg(arr)) {
            throw new IllegalArgsMatrixException();
        }
        MatrixAtomicImpl matrixAtomicImpl = new MatrixAtomicImpl(arr.length, arr[0].length);
        initMatrix(matrixAtomicImpl, arr);
        return matrixAtomicImpl;
    }

    private void initMatrix(MatrixAtomicImpl matrixAtomicImpl, int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                matrixAtomicImpl.setElement(i, j, new AtomicInteger(arr[i][j]));
            }
        }
    }


    private void initMatrix(int rows, int columns, MatrixAtomicImpl matrixElements) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixElements.setElement(i, j, new AtomicInteger());
            }
        }
    }
}
