package by.epam.task12.service.validator;

public class SquareMatrixValidator extends MatrixValidator {
    @Override
    public boolean validateMatrixArgs(int rows, int columns) {
        return super.validateMatrixArgs(rows, columns) && rows == columns;
    }
}
