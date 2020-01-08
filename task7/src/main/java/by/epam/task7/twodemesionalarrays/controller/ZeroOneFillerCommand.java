package by.epam.task7.exercise1.ex1.controller;

import by.epam.task7.exercise1.ex1.service.NullMatrixException;
import by.epam.task7.exercise1.ex1.service.SingleMultValuesFiller;
import by.epam.task7.twodemesionalarrays.entity.Matrix;
import by.epam.task7.twodemesionalarrays.factory.IllegalArgsMatrixException;
import by.epam.task7.twodemesionalarrays.factory.MatrixFactory;
import by.epam.task7.twodemesionalarrays.view.ShowMatrix;

public class ZeroOneFillerCommand {
    public static final int ROWS = 3;
    public static final int COLUMNS = 4;

    private MatrixFactory matrixFactory = new MatrixFactory();
    private SingleMultValuesFiller singleMultValuesFiller = new SingleMultValuesFiller();
    private ShowMatrix showMatrix = new ShowMatrix();

    public void execute() {
        try {
            Matrix matrix = matrixFactory.create(ROWS, COLUMNS);
            singleMultValuesFiller.fill(matrix, 1, 0);
            showMatrix.show(matrix);
        } catch (IllegalArgsMatrixException | NullMatrixException e) {
            e.printStackTrace();
        }
    }
}
