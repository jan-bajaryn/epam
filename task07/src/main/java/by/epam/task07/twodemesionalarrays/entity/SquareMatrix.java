package by.epam.task07.twodemesionalarrays.entity;

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
