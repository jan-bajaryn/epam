package by.epam.task07.twodemesionalarrays.entity;

public class Matrix {
    private int[][] arr;

    public Matrix(int rows, int columns) {
        arr = new int[rows][columns];
    }


    public int calcRows() {
        return arr == null ? 0 : arr.length;
    }

    public int calcColumns() {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return 0;
        } else {
            return arr[0].length;
        }
    }

    public void setElement(int row, int column, int value) {
        if (checkParams(row, column)) {
            return;
        }
        arr[row][column] = value;
    }

    /*
    @throws IllegalParamsException if there no so row or column in the matrix,
    need to check dimensions before use this method
    */
    public int calcElem(int row, int column) {
        if (checkParams(row, column)) {
            throw new IllegalParamsException();
        }
        return arr[row][column];
    }

    private boolean checkParams(int row, int column) {
        return row < 0 || row > (calcRows() - 1) || column < 0 || column > (calcColumns() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Matrix{\n");
        for (int i = 0; i < arr.length; i++) {
            int[] ints = arr[i];
            sb.append(i).append(". ->\t");
            for (int current : ints) {
                sb.append(current).append("\t");
            }
            sb.append("\n");
        }
        sb.append('}');
        return sb.toString();
    }
}