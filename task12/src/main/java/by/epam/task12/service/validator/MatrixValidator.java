package by.epam.task12.service.validator;


public class MatrixValidator {

    public boolean validateMatrixArgs(int rows, int columns) {
        return columns > 0 && rows > 0;
    }

    public boolean isValidArg(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return false;
        }

        int size = arr[0].length;

        if (size == 0) {
            return false;
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == null || arr[i].length != size) {
                return false;
            }
        }
        return true;
    }
}
