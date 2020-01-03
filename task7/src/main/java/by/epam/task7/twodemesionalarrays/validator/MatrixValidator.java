package by.epam.task7.twodemesionalarrays.validator;


public class MatrixValidator {

    public boolean validateMatrixArgs(int rows, int columns) {
        return columns > 0 && rows > 0;
    }

}
