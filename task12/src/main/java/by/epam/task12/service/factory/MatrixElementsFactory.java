package by.epam.task12.service.factory;

import by.epam.task12.entity.impl.Element;
import by.epam.task12.entity.impl.MatrixElements;
import by.epam.task12.entity.impl.MatrixImpl;
import by.epam.task12.service.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.service.validator.MatrixValidator;

public class MatrixElementsFactory {
    MatrixValidator matrixValidator = new MatrixValidator();

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
}
