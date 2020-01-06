package by.epam.task7.exercise4.ex40.service;

import by.epam.task7.exercise4.ex40.entity.SquareMatrix;
import by.epam.task7.twodemesionalarrays.service.FillWithIntegerMatrix;

import java.util.ArrayList;
import java.util.List;

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
//        System.out.println("not implemented");

    }

    private void dividedByFor(SquareMatrix squareMatrix) {


        for (int i = 0; i < squareMatrix.calcRows(); i += 4) {
            for (int j = 0; j < squareMatrix.calcRows(); j += 4) {
                fillDiagonals(squareMatrix, i, j, j + 3, -1);
            }
        }

        List<Integer> list = fillSymbolStraight(squareMatrix, 0);

        fillRemain(list, squareMatrix, -1);

    }

    private void fillDiagonals(SquareMatrix squareMatrix,
                               int beginRow,
                               int beginCol,
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

    private void fillRemain(List<Integer> list, SquareMatrix squareMatrix, int symbol) {
        int counter = 0;
        for (int i = squareMatrix.calcRows() - 1; i >= 0; i--) {
            for (int j = squareMatrix.calcRows() - 1; j >= 0; j--) {
                if (squareMatrix.calcElem(i, j) == symbol) {
                    squareMatrix.setElement(i, j, list.get(counter++));
                }
            }
        }
    }

    private List<Integer> fillSymbolStraight(SquareMatrix squareMatrix, int symbol) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < squareMatrix.calcRows(); i++) {
            for (int j = 0; j < squareMatrix.calcRows(); j++) {
                count++;
                if (squareMatrix.calcElem(i, j) == symbol) {
                    squareMatrix.setElement(i, j, count);
                } else {
                    list.add(count);
                }
            }
        }
        return list;
    }


    public void fillOddSquare(SquareMatrix squareMatrix) {

        fillWithIntegerMatrix.fillWithInteger(squareMatrix, -1);

        int rows = squareMatrix.calcRows();

        if (rows == 1) {
            squareMatrix.setElement(0, 0, 1);
            return;
        }


        int begin = rows / 2 + 1;


        int count = rows * rows;
        int curRow = begin - 1;
        int curCol = begin;


        int i = 1;
        do {
            squareMatrix.setElement(curRow, curCol, i);
            Pair next = next(rows, curRow, curCol, squareMatrix);
            curRow = next.getFst();
            curCol = next.getSec();
            i++;
        }
        while (i <= count);
    }

    private Pair next(int rows, int curRow, int curColumn, SquareMatrix matrix) {

        int row = (curRow - 1) < 0 ? rows - 1 : (curRow - 1);
        int col = (curColumn + 1) % rows;

        if (matrix.calcElem(row, col) == -1) {
            return new Pair(row, col);
        }
        row = curRow;
        col = (curColumn + 2) % rows;
        return new Pair(row, col);
    }
}
