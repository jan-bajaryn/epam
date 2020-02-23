package by.epam.task12.service.validator;


public class MatrixValidator {

    public boolean validateMatrixArgs(int rows, int columns) {
        return columns > 0 && rows > 0;
    }

}
