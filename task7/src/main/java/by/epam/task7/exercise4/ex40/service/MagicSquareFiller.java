package by.epam.task7.exercise4.ex40.service;

import by.epam.task7.exercise4.ex40.entity.SquareMatrix;
import by.epam.task7.twodemesionalarrays.service.FillWithIntegerMatrix;

import java.util.ArrayList;
import java.util.List;

public class MagicSquareFiller {

    private FillWithIntegerMatrix fillWithIntegerMatrix = new FillWithIntegerMatrix();


    public void fill(SquareMatrix matrix) throws UnresolvableException {
        if (matrix.calcRows() % 2 == 0) {
            fillEvenSquare(matrix);
        } else {
            fillOddSquare(matrix, 0, 0, matrix.calcRows() - 1, matrix.calcRows() - 1, 1);
        }
    }

    public void fillEvenSquare(SquareMatrix squareMatrix) throws UnresolvableException {
        if (squareMatrix.calcRows() == 4) {
            throw new UnresolvableException();
        }

        if (squareMatrix.calcRows() % 4 == 0) {
            dividedByFor(squareMatrix);
        } else {
            dividedByTwo(squareMatrix);
        }
    }

    private void dividedByTwo(SquareMatrix squareMatrix) {
        int rows = squareMatrix.calcRows();
        int middle = (rows / 2) - 1;
        int count = (middle + 1) * (middle + 1);

        fillOddSquare(squareMatrix, 0, 0, middle, middle, 1);

        fillOddSquare(squareMatrix, middle + 1, middle + 1, rows - 1, rows - 1, 1 + count);


        fillOddSquare(squareMatrix, 0, middle + 1, middle, rows - 1, 1 + 2 * count);


        fillOddSquare(squareMatrix, middle + 1, 0, rows - 1, middle, 1 + 3 * count);

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


    public void fillOddSquare(SquareMatrix squareMatrix, int bRow, int bCol, int eRow, int eCol, int beginValue) {

        fillWithIntegerMatrix.fillWithInteger(squareMatrix, bRow, bCol, eRow, eCol, -1);

        int rows = eRow - bRow + 1;

        if (rows == 1) {
            squareMatrix.setElement(0, 0, 1);
            return;
        }


//        int begin = ((eCol - bCol) / 2) + 1;

        int count = rows * rows;
//        int curRow = begin - 1;
//        int curCol = begin;

//        int curRow = ((eRow - bRow) / 2) + 1;
//        int curCol = ((eCol - bCol) / 2) + 2;


        int curRow = bRow + ((eRow - bRow) / 2);
        int curCol = bCol + ((eCol - bCol) / 2) + 1;

        int i = 1;
        int insertValue = beginValue;
        do {
            squareMatrix.setElement(curRow, curCol, insertValue++);
            Pair next = next(bRow, bCol, eRow, eCol, curRow, curCol, squareMatrix);
            curRow = next.getFst();
            curCol = next.getSec();
            i++;
        }
        while (i <= count);
    }

    private Pair next(int bRow, int bCol, int eRow, int eCol, int curRow, int curColumn, SquareMatrix matrix) {

        int row = (curRow - 1) < bRow ? eRow : (curRow - 1);
        int col = (curColumn + 1 > eCol) ? bCol : curColumn + 1;

        if (matrix.calcElem(row, col) == -1) {
            return new Pair(row, col);
        }


        row = curRow;

        col = (curColumn + 1 > eCol) ? bCol : curColumn + 1;
        col = (col + 1 > eCol) ? bCol : col + 1;


        return new Pair(row, col);
    }
}
