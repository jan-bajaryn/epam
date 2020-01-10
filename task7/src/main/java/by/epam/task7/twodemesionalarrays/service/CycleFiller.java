package by.epam.task7.twodemesionalarrays.service;

import by.epam.task7.twodemesionalarrays.entity.SquareMatrix;
import by.epam.task7.twodemesionalarrays.service.exception.NullMatrixException;

public class CycleFiller {


    public void fillCycle(SquareMatrix squareMatrix, int beginValue) throws NullMatrixException {


        if (squareMatrix == null) {
            throw new NullMatrixException("Matrix can't be null");
        }
        int rows = squareMatrix.calcRows();
        int temp = rows;
        Pair begin = new Pair(0, 0);

        for (int i = 0; i < rows / 2 - 1; i++) {
            for (int j = 0; j < temp - 1; j++) {
                squareMatrix.setElement(begin.getFst(), begin.getSec(), beginValue++);
                begin = goRight(rows, begin);
            }
            squareMatrix.setElement(begin.getFst(), begin.getSec(), beginValue++);
            begin = goDown(rows, begin);


            --temp;
            for (int j = 0; j < temp - 1; j++) {
                squareMatrix.setElement(begin.getFst(), begin.getSec(), beginValue++);
                begin = goDown(rows, begin);
            }

            squareMatrix.setElement(begin.getFst(), begin.getSec(), beginValue++);
            begin = goLeft(begin);


            for (int j = 0; j < temp - 1; j++) {
                squareMatrix.setElement(begin.getFst(), begin.getSec(), beginValue++);
                begin = goLeft(begin);
            }
            squareMatrix.setElement(begin.getFst(), begin.getSec(), beginValue++);
            begin = goUp(begin);


            --temp;

            for (int j = 0; j < temp - 1; j++) {
                squareMatrix.setElement(begin.getFst(), begin.getSec(), beginValue++);
                begin = goUp(begin);
            }
            squareMatrix.setElement(begin.getFst(), begin.getSec(), beginValue++);
            begin = goRight(rows, begin);
        }
        fillRemain(rows, squareMatrix, beginValue, begin);
    }

    private void fillRemain(int rows, SquareMatrix squareMatrix, int beginValue, Pair pair) {
        if (rows % 2 == 0) {
            squareMatrix.setElement(pair.getFst(), pair.getSec(), beginValue++);
            squareMatrix.setElement(pair.getFst(), pair.getSec() + 1, beginValue++);
            squareMatrix.setElement(pair.getFst() + 1, pair.getSec() + 1, beginValue++);
            squareMatrix.setElement(pair.getFst() + 1, pair.getSec(), beginValue);
        } else {
            squareMatrix.setElement(pair.getFst(), pair.getSec(), beginValue++);
            squareMatrix.setElement(pair.getFst(), pair.getSec() + 1, beginValue++);
            squareMatrix.setElement(pair.getFst(), pair.getSec() + 2, beginValue++);
            squareMatrix.setElement(pair.getFst() + 1, pair.getSec() + 2, beginValue++);
            squareMatrix.setElement(pair.getFst() + 2, pair.getSec() + 2, beginValue++);
            squareMatrix.setElement(pair.getFst() + 2, pair.getSec() + 1, beginValue++);
            squareMatrix.setElement(pair.getFst() + 2, pair.getSec(), beginValue++);
            squareMatrix.setElement(pair.getFst() + 1, pair.getSec(), beginValue++);
            squareMatrix.setElement(pair.getFst() + 1, pair.getSec() + 1, beginValue);

        }
    }

    private Pair goRight(int rows, Pair pair) {
        if (pair.getSec() == rows - 1) {
            return new Pair(pair.getFst(), pair.getSec());
        } else {
            return new Pair(pair.getFst(), pair.getSec() + 1);
        }
    }

    private Pair goLeft(Pair pair) {
        if (pair.getSec() == 0) {
            return new Pair(pair.getFst(), pair.getSec());
        } else {
            return new Pair(pair.getFst(), pair.getSec() - 1);
        }
    }

    private Pair goUp(Pair pair) {
        if (pair.getFst() == 0) {
            return new Pair(pair.getFst(), pair.getSec());
        } else {
            return new Pair(pair.getFst() - 1, pair.getSec());
        }
    }

    private Pair goDown(int rows, Pair pair) {
        if (pair.getFst() == rows - 1) {
            return new Pair(pair.getFst(), pair.getSec());
        } else {
            return new Pair(pair.getFst() + 1, pair.getSec());
        }
    }
}
