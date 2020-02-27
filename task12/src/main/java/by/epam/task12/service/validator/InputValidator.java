package by.epam.task12.service.validator;

import by.epam.task12.entity.Matrix;

public class InputValidator {
    public boolean isValid(Matrix matrix, int[] arr) {
        if (matrix == null || matrix.calcRows() != matrix.calcColumns() || matrix.calcColumns() < 8 || matrix.calcColumns() > 12) {
            return false;
        }
        return arr != null && arr.length >= 4 && arr.length <= 6;
    }
}
