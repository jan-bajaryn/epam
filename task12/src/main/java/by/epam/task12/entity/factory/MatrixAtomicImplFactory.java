package by.epam.task12.entity.factory;

import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.entity.impl.Element;
import by.epam.task12.entity.impl.MatrixAtomicImpl;
import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.service.validator.MatrixValidator;

import java.util.concurrent.atomic.AtomicInteger;

public class MatrixAtomicImplFactory {
    MatrixValidator matrixValidator = new MatrixValidator();

    public MatrixAtomicImpl create(int rows, int columns) throws IllegalArgsMatrixException {
        if (!matrixValidator.validateMatrixArgs(rows, columns)) {
            throw new IllegalArgsMatrixException();
        }

        MatrixAtomicImpl matrixElements = new MatrixAtomicImpl(rows, columns);
        initMatrix(rows, columns, matrixElements);
        return matrixElements;
    }

    private void initMatrix(int rows, int columns, MatrixAtomicImpl matrixElements) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixElements.setElement(i, j, new AtomicInteger());
            }
        }
    }
}
