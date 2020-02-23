package by.epam.task12.service.factory;


import by.epam.task12.entity.impl.MatrixImpl;
import by.epam.task12.service.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.service.validator.MatrixValidator;

public class MatrixFactory {
    MatrixValidator matrixValidator = new MatrixValidator();

    public MatrixImpl create(int rows, int columns) throws IllegalArgsMatrixException {
        if (!matrixValidator.validateMatrixArgs(rows, columns)) {
            throw new IllegalArgsMatrixException();
        }

        return new MatrixImpl(rows, columns);

    }
}
