package by.epam.task7.twodemesionalarrays.factory;

import by.epam.task7.twodemesionalarrays.entity.SquareMatrix;
import by.epam.task7.twodemesionalarrays.factory.exception.IllegalArgsMatrixException;
import by.epam.task7.twodemesionalarrays.factory.exception.IllegalArgsSquareMatrixException;
import by.epam.task7.twodemesionalarrays.validator.SquareMatrixValidator;

public class SquareMatrixFactory extends MatrixFactory {
    private SquareMatrixValidator squareMatrixValidator = new SquareMatrixValidator();

    @Override
    public SquareMatrix create(int rows, int columns) throws IllegalArgsMatrixException {
        if (!squareMatrixValidator.validateMatrixArgs(rows, columns)) {
            throw new IllegalArgsSquareMatrixException();
        }
        return new SquareMatrix(rows);
    }

    public SquareMatrix create(int rows) throws IllegalArgsSquareMatrixException{
        if (!squareMatrixValidator.validateMatrixArgs(rows, rows)) {
            throw new IllegalArgsSquareMatrixException();
        }
        return new SquareMatrix(rows);
    }
}
