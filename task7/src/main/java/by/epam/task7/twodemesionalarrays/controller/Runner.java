package by.epam.task7.twodemesionalarrays.controller;


import by.epam.task7.twodemesionalarrays.entity.Matrix;
import by.epam.task7.twodemesionalarrays.factory.IllegalArgsMatrixException;
import by.epam.task7.twodemesionalarrays.factory.MatrixFactory;
import by.epam.task7.twodemesionalarrays.service.FillRandomlyMatrix;
import by.epam.task7.twodemesionalarrays.service.FillWithIntegerMatrix;
import by.epam.task7.twodemesionalarrays.service.IllegalArgGenerationException;
import by.epam.task7.twodemesionalarrays.view.ShowMatrix;

public class Runner {
    public static void main(String[] args) {
        MatrixFactory matrixFactory = new MatrixFactory();
        ShowMatrix showMatrix = new ShowMatrix();
        FillRandomlyMatrix fillRandomlyMatrix = new FillRandomlyMatrix();
        FillWithIntegerMatrix fillWithIntegerMatrix = new FillWithIntegerMatrix();
        try {
            Matrix matrix = matrixFactory.create(10, 20);
            showMatrix.show(matrix);
            System.out.println(matrix.calcRows());
            System.out.println(matrix.calcColumns());
            fillRandomlyMatrix.fillRandomly(matrix, 0, 3);
            showMatrix.show(matrix);
            fillWithIntegerMatrix.fillWithInteger(matrix, 54);
            showMatrix.show(matrix);
        } catch (IllegalArgsMatrixException | IllegalArgGenerationException e) {
            e.printStackTrace();
        }

    }
}
