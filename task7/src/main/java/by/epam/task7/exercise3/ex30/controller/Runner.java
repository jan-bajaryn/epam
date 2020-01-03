package by.epam.task7.exercise3.ex30.controller;

import by.epam.task7.twodemesionalarrays.entity.Matrix;
import by.epam.task7.twodemesionalarrays.factory.IllegalArgsMatrixException;
import by.epam.task7.twodemesionalarrays.factory.MatrixFactory;
import by.epam.task7.twodemesionalarrays.service.FillRandomlyMatrix;
import by.epam.task7.twodemesionalarrays.service.IllegalArgGenerationException;
import by.epam.task7.twodemesionalarrays.view.ShowMatrix;

public class Runner {
    public static void main(String[] args) {
        MatrixFactory matrixFactory = new MatrixFactory();
        FillRandomlyMatrix fillRandomlyMatrix = new FillRandomlyMatrix();
        ShowMatrix showMatrix = new ShowMatrix();
        try {
            Matrix matrix = matrixFactory.create(100, 5);
            fillRandomlyMatrix.fillRandomly(matrix, 2, 6);
            showMatrix.show(matrix);
            CheckerRowsCommand checkerRowsCommand = new CheckerRowsCommand();
            checkerRowsCommand.execute(matrix, 5, 3);
        } catch (IllegalArgsMatrixException e) {
            e.printStackTrace();
        } catch (IllegalArgGenerationException e) {
            e.printStackTrace();
        }
    }
}
