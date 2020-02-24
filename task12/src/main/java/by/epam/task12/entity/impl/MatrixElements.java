package by.epam.task12.entity.impl;

import by.epam.task12.entity.IllegalParamsException;
import by.epam.task12.entity.Matrix;

public class MatrixElements implements Matrix {
    private Element[][] arr;

    public MatrixElements(int rows, int columns) {
        arr = new Element[rows][columns];
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
        arr[row][column].setValue(value);
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
        return arr[row][column].getValue();
    }

    private boolean checkParams(int row, int column) {
        return row < 0 || row > (calcRows() - 1) || column < 0 || column > (calcColumns() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Matrix{\n");
        for (int i = 0; i < arr.length; i++) {
            Element[] elements = arr[i];
            sb.append(i).append(". ->\t");
            for (Element current : elements) {
                sb.append(current.getValue()).append("\t");
            }
            sb.append("\n");
        }
        sb.append('}');
        return sb.toString();
    }

    public Element getElement(int row, int column) {
        if (checkParams(row, column)) {
            throw new IllegalParamsException();
        }
        return arr[row][column];
    }

    public void setElement(int row, int column, Element element) {
        arr[row][column] = element;
    }
}
