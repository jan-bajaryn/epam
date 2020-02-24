package by.epam.task12.service.validator;

import by.epam.task12.entity.impl.MatrixImpl;

public class DiagonalZeroChecker {
    public boolean isValid(MatrixImpl matrixImpl) {
        if (matrixImpl == null) {
            return false;
        }

        int rows = matrixImpl.calcRows();
        int columns = matrixImpl.calcColumns();

        for (int i = 0; i < rows; i++) {
            if (i <= columns - 1) {
                if (matrixImpl.calcValue(i, i) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
