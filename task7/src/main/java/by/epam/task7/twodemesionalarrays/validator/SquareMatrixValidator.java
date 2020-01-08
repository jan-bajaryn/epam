package by.epam.task7.exercise4.ex40.validator;

import by.epam.task7.twodemesionalarrays.validator.MatrixValidator;

public class SquareMatrixValidator extends MatrixValidator {
    @Override
    public boolean validateMatrixArgs(int rows, int columns) {
        return super.validateMatrixArgs(rows, columns) && rows == columns;
    }
}
