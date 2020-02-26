package by.epam.task12.service.filler;

import by.epam.task12.entity.Matrix;

public interface DiagonalFiller<T extends Matrix> {
    void fill(T matrixImpl, int[] arr);
}
