package by.epam.task12.entity.impl;

public class SquareMatrixImpl extends MatrixImpl {
    public SquareMatrixImpl(int rows) {
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
