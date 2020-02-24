package by.epam.task12.entity;

public interface Matrix {
    int calcRows();

    int calcColumns();

    void setValue(int row, int column, int value);

    /*
        @throws IllegalParamsException if there no so row or column in the matrix,
        need to check dimensions before use this method
        */
    Integer calcValue(int row, int column);
}
