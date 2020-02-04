package by.epam.task07.twodemesionalarrays.validator;


public class MatrixValidator {

    public boolean validateMatrixArgs(int rows, int columns) {
        return columns > 0 && rows > 0;
    }

}
