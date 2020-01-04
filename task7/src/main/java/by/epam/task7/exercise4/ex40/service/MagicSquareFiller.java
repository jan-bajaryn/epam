package by.epam.task7.exercise4.ex40.service;

import by.epam.task7.exercise4.ex40.entity.SquareMatrix;
import by.epam.task7.twodemesionalarrays.service.FillWithIntegerMatrix;

public class MagicSquareFiller {

    private FillWithIntegerMatrix fillWithIntegerMatrix = new FillWithIntegerMatrix();


    public void fill(SquareMatrix matrix) {
        if (matrix.calcRows() % 2 == 0) {
            fillEvenSquare(matrix);
        } else {
            fillOddSquare(matrix);
        }
    }

    public void fillEvenSquare(SquareMatrix squareMatrix) {
        if (squareMatrix.calcRows() % 4 == 0) {
            dividedByFor(squareMatrix);
        } else {
            dividedByTwo(squareMatrix);
        }
    }

    private void dividedByTwo(SquareMatrix squareMatrix) {

    }

    private void dividedByFor(SquareMatrix squareMatrix) {
        int lastIndex = squareMatrix.calcRows() - 1;
        int middle = squareMatrix.calcRows() / 2;
        fillDiagonals(squareMatrix, 0, 0,
                middle - 1, middle - 1, -1);
        fillDiagonals(squareMatrix, 0, middle, middle - 1, lastIndex, -1);
        fillDiagonals(squareMatrix, middle, 0, lastIndex, middle - 1, -1);
        fillDiagonals(squareMatrix, middle, middle, lastIndex, lastIndex, -1);
    }

    private void fillDiagonals(SquareMatrix squareMatrix,
                               int beginRow,
                               int beginCol,
                               int lastRow,
                               int lastCol,
                               int value) {
        int tempRowBeg = beginRow;
        int tempColBeg = beginCol;
        int tempRowLst = beginRow;
        int tempColLst = lastCol;

        while (tempColBeg <= lastCol) {
            squareMatrix.setElement(tempRowBeg++, tempColBeg++, value);
            squareMatrix.setElement(tempRowLst++, tempColLst--, value);
        }

    }


    public void fillOddSquare(SquareMatrix squareMatrix) {

        fillWithIntegerMatrix.fillWithInteger(squareMatrix, -1);

        int rows = squareMatrix.calcRows();

        if (rows == 1) {
            squareMatrix.setElement(0, 0, 1);
            return;
        }


        int begin = rows / 2 + 1;
        System.out.println("begin = " + begin);


        int count = rows * rows;
        int curRow = begin - 1;
        int curCol = begin;


        int i = 1;
        do {
            System.out.println("Current = " + i);
            squareMatrix.setElement(curRow, curCol, i);
            Pair next = next(rows, curRow, curCol, squareMatrix);
            curRow = next.fst;
            curCol = next.sec;
            i++;
        }
        while (i <= count);
    }

    private Pair next(int rows, int curRow, int curColumn, SquareMatrix matrix) {

        System.out.println("curRow = " + curRow + ", curColumn = " + curColumn);
        int row = (curRow - 1) < 0 ? rows - 1 : (curRow - 1);
        int col = (curColumn + 1) % rows;

        System.out.println("row = " + row + ", column = " + col);
        if (matrix.calcElem(row, col) == -1) {
            return new Pair(row, col);
        }
        row = curRow;
        col = (curColumn + 2) % rows;
        return new Pair(row, col);
    }
}
