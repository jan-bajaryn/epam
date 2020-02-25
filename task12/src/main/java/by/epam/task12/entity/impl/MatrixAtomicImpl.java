package by.epam.task12.entity.impl;

import by.epam.task12.entity.IllegalParamsException;
import by.epam.task12.entity.Matrix;

import java.util.concurrent.atomic.AtomicInteger;

public class MatrixAtomicImpl implements Matrix {
    private AtomicInteger[][] arr;

    public MatrixAtomicImpl(int rows, int columns) {
        arr = new AtomicInteger[rows][columns];
    }


    @Override
    public int calcRows() {
        return arr == null ? 0 : arr.length;
    }

    @Override
    public int calcColumns() {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return 0;
        } else {
            return arr[0].length;
        }
    }

    @Override
    public void setValue(int row, int column, int value) {
        if (checkParams(row, column)) {
            return;
        }
        arr[row][column] = new AtomicInteger(value);
    }

    /*
    @throws IllegalParamsException if there no so row or column in the matrix,
    need to check dimensions before use this method
    */
    @Override
    public Integer calcValue(int row, int column) {
        if (checkParams(row, column)) {
            throw new IllegalParamsException();
        }
        return arr[row][column].get();
    }

    private boolean checkParams(int row, int column) {
        return row < 0 || row > (calcRows() - 1) || column < 0 || column > (calcColumns() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Matrix{\n");
        for (int i = 0; i < arr.length; i++) {
            AtomicInteger[] elements = arr[i];
            sb.append(i).append(". ->\t");
            for (AtomicInteger current : elements) {
                sb.append(current.get()).append("\t");
            }
            sb.append("\n");
        }
        sb.append('}');
        return sb.toString();
    }

    public AtomicInteger getElement(int row, int column) {
        if (checkParams(row, column)) {
            throw new IllegalParamsException();
        }
        return arr[row][column];
    }

    public void setElement(int row, int column, AtomicInteger atomicInteger) {
        arr[row][column] = atomicInteger;
    }
}
