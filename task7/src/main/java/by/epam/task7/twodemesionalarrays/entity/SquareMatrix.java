package by.epam.task7.twodemesionalarrays.entity;

import by.epam.task7.twodemesionalarrays.entity.Matrix;

public class SquareMatrix extends Matrix {
    public SquareMatrix(int rows) {
        super(rows, rows);
    }

    @Override
    public int calcRows() {
        return super.calcRows();
    }

    @Override
    public int calcColumns() {
        return calcRows();
    }

}
