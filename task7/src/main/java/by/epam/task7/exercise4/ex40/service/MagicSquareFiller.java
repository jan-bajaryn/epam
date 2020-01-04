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

    }

    private void dividedByFor(SquareMatrix squareMatrix) {
        int lastIndex = squareMatrix.calcRows() - 1;
        int middle = squareMatrix.calcRows() / 2;
        fillDiagonals(squareMatrix, 0, 0,
                middle - 1, -1);
        fillDiagonals(squareMatrix, 0, middle, lastIndex, -1);
        fillDiagonals(squareMatrix, middle, 0, middle - 1, -1);
        fillDiagonals(squareMatrix, middle, middle, lastIndex, -1);

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


//        swapAutomatically(squareMatrix, -1);
    }

    private void fillRemain(List<Integer> list, SquareMatrix squareMatrix, int symbol) {
        int counter = 0;
        System.out.println(list);
        for (int i = squareMatrix.calcRows() - 1; i >= 0; i--) {
            for (int j = squareMatrix.calcRows() - 1; j >= 0; j--) {
                if (squareMatrix.calcElem(i, j) == symbol) {
                    squareMatrix.setElement(i, j, list.get(counter++));
                }
            }
        }
    }

//    private void swapAutomatically(SquareMatrix squareMatrix, int symbol) {
//        int beginCounter = 1;
//        int lastCounter = squareMatrix.calcRows();
//
//        int bRow = 0;
//        int bCol = 0;
//
//        Pair bPair = new Pair(bRow,bCol);
//
//        int lRow = lastCounter - 1;
//        int lCol = lRow;
//
//        Pair lPair = new Pair(lRow,lCol);

//        int fPos =0;
//        int lPos = lRow;

//        boolean isDone = false;
//        while (bRow <= lRow /*|| bCol < lCol*/) {
//            for (int i = bRow; i <= lRow; i++) {
//                for (int j = bCol; j < squareMatrix.calcRows(); j++) {
//                    if (squareMatrix.calcElem(bRow, bCol) == symbol) {
//                        break;
//                    }
//                }
//                bCol = 0;
//            }
//            Pair fstPair = findFirstPositions(squareMatrix,bRow,lRow,bCol)

    // find next position with symbol

//            bPair = findFirstPosition(squareMatrix, bPair);
//            if (bPair != null) {
//                lPair = findSecondPosition(squareMatrix, lPair);
//
//                squareMatrix.setElement(bPair.getFst(),bPair.getSec(),lastCounter);
//                squareMatrix.setElement(lPair.getFst(),lPair.getSec(),beginCounter);
//            }

    // find next position with symbol


//        }
//
//    }

//    private Pair straightNextSquareMatrix(int rows, Pair pair) {
//        if (pair.getFst() == rows - 1) {
//            if (pair.getSec() == rows - 1) {
//                return new Pair(pair.getFst(), pair.getSec());
//            } else {
//                return new Pair(0, pair.getSec() + 1);
//            }
//        } else {
//            return new Pair(pair.getFst() + 1, pair.getSec());
//        }
//    }
//
//    private Pair straightPrevSquareMatrix(int rows, Pair pair) {
//        if (pair.getFst() == 0) {
//            if (pair.getSec() == 0) {
//                return new Pair(0, 0);
//            } else {
//                return new Pair(rows - 1, pair.getSec() - 1);
//            }
//        } else {
//            return new Pair(pair.getFst() - 1, pair.getSec());
//        }
//    }

//    private void swap(SquareMatrix squareMatrix, int bRow, int bCol, int lRow, int lCol) {
//        int temp = squareMatrix.calcElem(bRow, bCol);
//        squareMatrix.setElement(bRow, bCol, squareMatrix.calcElem(lRow, lCol));
//        squareMatrix.setElement(lRow, lCol, temp);
//    }

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
        System.out.println("begin = " + begin);


        int count = rows * rows;
        int curRow = begin - 1;
        int curCol = begin;


        int i = 1;
        do {
            System.out.println("Current = " + i);
            squareMatrix.setElement(curRow, curCol, i);
            Pair next = next(rows, curRow, curCol, squareMatrix);
            curRow = next.getFst();
            curCol = next.getSec();
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
