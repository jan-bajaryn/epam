package by.epam.task7.twodemesionalarrays.factory;

import by.epam.task7.twodemesionalarrays.entity.Matrix;
import by.epam.task7.twodemesionalarrays.validator.MatrixValidator;

public class MatrixFactory {
    MatrixValidator matrixValidator = new MatrixValidator();

    public Matrix create(int rows, int columns) throws IllegalArgsMatrixException {
        if (!matrixValidator.validateMatrixArgs(rows, columns)) {
            throw new IllegalArgsMatrixException();
        }

        return new Matrix(rows, columns);

    }
}
