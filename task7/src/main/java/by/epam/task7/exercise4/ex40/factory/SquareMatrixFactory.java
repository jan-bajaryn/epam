package by.epam.task7.exercise4.ex40.factory;

import by.epam.task7.exercise4.ex40.entity.SquareMatrix;
import by.epam.task7.exercise4.ex40.validator.SquareMatrixValidator;
import by.epam.task7.twodemesionalarrays.factory.IllegalArgsMatrixException;
import by.epam.task7.twodemesionalarrays.factory.MatrixFactory;

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
