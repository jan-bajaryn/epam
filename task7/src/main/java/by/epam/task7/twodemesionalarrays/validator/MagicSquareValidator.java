package by.epam.task7.twodemesionalarrays.validator;

import by.epam.task7.twodemesionalarrays.entity.SquareMatrix;
import by.epam.task7.twodemesionalarrays.service.DuplicateFinderImpl;
import by.epam.task7.twodemesionalarrays.service.MaxMinFinder;

public class MagicSquareValidator {

    private DuplicateFinderImpl duplicateFinderImpl = new DuplicateFinderImpl();
    private MaxMinFinder maxMinFinder = new MaxMinFinder();

    public boolean isValid(SquareMatrix matrix) {
        int rows = matrix.calcRows();
        if (maxMinFinder.findMaximum(matrix) > Math.pow(rows, 2)
                || maxMinFinder.findMinimum(matrix) < 1) {
//            System.out.println("maximum or minimum");
            return false;
        }

        if (rows > 400 && rows < 2000) {
            if (duplicateFinderImpl.isDuplicatedMagicSquare(matrix)) {
//                System.out.println("duplication");
                return false;
            }
        } else {
            if (duplicateFinderImpl.isDuplicatedValues(matrix)) {
//                System.out.println("duplication");
                return false;
            }
        }


        int sum = sumDiagonalFirst(matrix, rows);
        if (sum != sumDiagonalSecond(matrix, rows)) {
//            System.out.println("sum diagonals");
//            System.out.println("first diagonal = " + sum);
//            System.out.println("second diagonal = " + sumDiagonalSecond(matrix, rows));
            return false;
        }
        for (int i = 0; i < rows; i++) {
            if (sum != sumColumn(i, matrix, rows) || sum != sumRow(i, matrix, rows)) {
//                System.out.println("sum columns or rows");
                return false;
            }
        }
        return true;
    }


    private int sumColumn(int i, SquareMatrix matrix, int rows) {

        int sum = 0;
        for (int j = 0; j < rows; j++) {
            sum += matrix.calcElem(j, i);
        }
        return sum;
    }

    private int sumRow(int i, SquareMatrix matrix, int rows) {
        int sum = 0;
        for (int j = 0; j < rows; j++) {
            sum += matrix.calcElem(i, j);
        }
        return sum;
    }

    private int sumDiagonalFirst(SquareMatrix matrix, int rows) {
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            sum += matrix.calcElem(i, i);
        }
        return sum;
    }

    private int sumDiagonalSecond(SquareMatrix matrix, int rowsCount) {
        int sum = 0;
        int column = rowsCount - 1;
        int row = 0;
        while (column >= 0) {
            sum += matrix.calcElem(row, column);

            column--;
            row++;
        }
        return sum;
    }

}
