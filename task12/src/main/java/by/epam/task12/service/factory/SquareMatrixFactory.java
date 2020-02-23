package by.epam.task12.service.factory;


import by.epam.task12.entity.impl.SquareMatrixImpl;
import by.epam.task12.service.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.service.factory.exception.IllegalArgsSquareMatrixException;
import by.epam.task12.service.validator.SquareMatrixValidator;

public class SquareMatrixFactory extends MatrixFactory {
    private SquareMatrixValidator squareMatrixValidator = new SquareMatrixValidator();

    @Override
    public SquareMatrixImpl create(int rows, int columns) throws IllegalArgsMatrixException {
        if (!squareMatrixValidator.validateMatrixArgs(rows, columns)) {
            throw new IllegalArgsSquareMatrixException();
        }
        return new SquareMatrixImpl(rows);
    }

    public SquareMatrixImpl create(int rows) throws IllegalArgsSquareMatrixException{
        if (!squareMatrixValidator.validateMatrixArgs(rows, rows)) {
            throw new IllegalArgsSquareMatrixException();
        }
        return new SquareMatrixImpl(rows);
    }
}
