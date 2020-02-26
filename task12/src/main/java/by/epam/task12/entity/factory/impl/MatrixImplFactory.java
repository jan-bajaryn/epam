package by.epam.task12.entity.factory.impl;


import by.epam.task12.entity.factory.MatrixFactory;
import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.entity.impl.MatrixImpl;
import by.epam.task12.service.validator.MatrixValidator;

public class MatrixImplFactory implements MatrixFactory<MatrixImpl> {
    MatrixValidator matrixValidator = new MatrixValidator();

    @Override
    public MatrixImpl create(int rows, int columns) throws IllegalArgsMatrixException {
        if (!matrixValidator.validateMatrixArgs(rows, columns)) {
            throw new IllegalArgsMatrixException();
        }

        return new MatrixImpl(rows, columns);

    }

    @Override
    public MatrixImpl create(int[][] arr) throws IllegalArgsMatrixException {
        if (!matrixValidator.isValidArg(arr)) {
            throw new IllegalArgsMatrixException();
        }
        MatrixImpl matrixImpl = new MatrixImpl(arr.length, arr[0].length);
        initMatrix(matrixImpl, arr);
        return matrixImpl;
    }

    private void initMatrix(MatrixImpl matrixAtomicImpl, int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                matrixAtomicImpl.setValue(i, j, arr[i][j]);
            }
        }
    }

}
