package by.epam.task07.twodemesionalarrays.service;

import by.epam.task07.twodemesionalarrays.entity.Matrix;

public class ElementSwapperMatrix {
    public void swap(Matrix matrix, int fRow, int fCol, int sRow, int sCol) {
        int temp = matrix.calcElem(fRow, fCol);
        matrix.setElement(fRow, fCol, matrix.calcElem(sRow, sCol));
        matrix.setElement(sRow, sCol, temp);
    }
}
