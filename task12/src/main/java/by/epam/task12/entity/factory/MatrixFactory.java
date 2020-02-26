package by.epam.task12.entity.factory;

import by.epam.task12.entity.Matrix;
import by.epam.task12.entity.factory.exception.IllegalArgsMatrixException;

public interface MatrixFactory<T extends Matrix> {
    T create(int rows, int columns) throws IllegalArgsMatrixException;
    T create(int[][] arr) throws IllegalArgsMatrixException;
}
