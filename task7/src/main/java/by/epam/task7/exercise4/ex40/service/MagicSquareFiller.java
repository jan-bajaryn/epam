package by.epam.task7.exercise4.ex40.service;

import by.epam.task7.exercise4.ex40.entity.SquareMatrix;

public class MagicSquareFiller {
    public void fill(SquareMatrix matrix) {
        if (matrix.calcRows() % 2 == 0) {
            fillEvenSquare(matrix);
        } else {
            fillOddSquare(matrix);
        }
    }

    public void fillEvenSquare(SquareMatrix squareMatrix) {
        int rows = squareMatrix.calcRows();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                int value = 1 + ((i + j - 1 + (rows - 1) / 2) % rows) * rows + ((i + 2 * j + 2) % rows);
                squareMatrix.setElement(i, j, value);
            }
        }

    }

    public void fillOddSquare(SquareMatrix squareMatrix) {
        System.out.println("not implemented");
    }
}
