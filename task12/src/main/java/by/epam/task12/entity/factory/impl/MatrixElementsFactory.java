package by.epam.task12.entity.factory.impl;

import by.epam.task12.entity.Element;
import by.epam.task12.entity.factory.MatrixFactory;
import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.service.validator.MatrixValidator;

public class MatrixElementsFactory implements MatrixFactory<MatrixElements> {
    MatrixValidator matrixValidator = new MatrixValidator();

    @Override
    public MatrixElements create(int rows, int columns) throws IllegalArgsMatrixException {
        if (!matrixValidator.validateMatrixArgs(rows, columns)) {
            throw new IllegalArgsMatrixException();
        }

        MatrixElements matrixElements = new MatrixElements(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixElements.setElement(i, j, new Element());
            }
        }
        return matrixElements;
    }

    @Override
    public MatrixElements create(int[][] arr) throws IllegalArgsMatrixException {
        if (!matrixValidator.isValidArg(arr)) {
            throw new IllegalArgsMatrixException();
        }
        MatrixElements matrixElements = new MatrixElements(arr.length, arr[0].length);
        initMatrix(matrixElements, arr);
        return matrixElements;
    }

    private void initMatrix(MatrixElements matrixAtomicImpl, int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                matrixAtomicImpl.setElement(i, j, new Element(arr[i][j]));
            }
        }
    }
}
