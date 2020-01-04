package by.epam.task7.cyclefiller.service;

import by.epam.task7.exercise4.ex40.entity.SquareMatrix;
import by.epam.task7.exercise4.ex40.service.Pair;

public class CycleFiller {
    public void fillCycle(SquareMatrix squareMatrix,int beginValue) {
        int counter = 0;
        Pair position = new Pair(0, 0);
        int counter2 = beginValue;
        for (int i = beginValue; i < squareMatrix.calcRows() / 2+beginValue; i++) {
            System.out.println("squareMatrix.calcRows()- counter = " + (squareMatrix.calcRows() - counter));
            for (int j = counter; j < squareMatrix.calcRows() - counter-1; j++) {
                position = goRight(squareMatrix.calcRows(), position);
                squareMatrix.setElement(position.getFst(), position.getSec(), counter2++);
            }
            for (int j = counter; j < squareMatrix.calcRows() - counter-1; j++) {
                position = goDown(squareMatrix.calcRows(), position);
                squareMatrix.setElement(position.getFst(), position.getSec(), counter2++);
            }
            for (int j = counter; j < squareMatrix.calcRows() - counter-1; j++) {
                position = goLeft(squareMatrix.calcRows(), position);
                squareMatrix.setElement(position.getFst(), position.getSec(), counter2++);
            }
            for (int j = counter; j < squareMatrix.calcRows() - counter-1; j++) {
                position = goUp(squareMatrix.calcRows(), position);
                squareMatrix.setElement(position.getFst(), position.getSec(), counter2++);
            }
            counter++;
            position = goRight(squareMatrix.calcRows(), position);
            position = goDown(squareMatrix.calcRows(), position);
        }

    }

    private Pair goRight(int rows, Pair pair) {
        if (pair.getFst() == rows - 1) {
            return new Pair(pair.getFst(), pair.getSec());
        } else {
            return new Pair(pair.getFst() + 1, pair.getSec());
        }
    }

    private Pair goLeft(int rows, Pair pair) {
        if (pair.getFst() == 0) {
            return new Pair(pair.getFst(), pair.getSec());
        } else {
            return new Pair(pair.getFst() - 1, pair.getSec());
        }
    }

    private Pair goUp(int rows, Pair pair) {
        if (pair.getSec() == 0) {
            return new Pair(pair.getFst(), pair.getSec());
        } else {
            return new Pair(pair.getFst(), pair.getSec() - 1);
        }
    }

    private Pair goDown(int rows, Pair pair) {
        if (pair.getSec() == rows - 1) {
            return new Pair(pair.getFst(), pair.getSec());
        } else {
            return new Pair(pair.getFst(), pair.getSec() + 1);
        }
    }
}
