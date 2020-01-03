package by.epam.task7.exercise2.ex11.controller;

import by.epam.task7.twodemesionalarrays.entity.Matrix;
import by.epam.task7.twodemesionalarrays.factory.IllegalArgsMatrixException;
import by.epam.task7.twodemesionalarrays.factory.MatrixFactory;
import by.epam.task7.twodemesionalarrays.service.FillRandomlyMatrix;
import by.epam.task7.twodemesionalarrays.service.IllegalArgGenerationException;
import by.epam.task7.twodemesionalarrays.view.ShowMatrix;

import java.lang.reflect.Modifier;

public class Runner {
    public static void main(String[] args) {
        MatrixFactory matrixFactory = new MatrixFactory();
        OddReverseCommand oddReverseCommand = new OddReverseCommand();
        ShowMatrix showMatrix = new ShowMatrix();
        FillRandomlyMatrix fillRandomlyMatrix = new FillRandomlyMatrix();
        try {
            Matrix matrix = matrixFactory.create(4, 10);
            fillRandomlyMatrix.fillRandomly(matrix,3,34);
            showMatrix.show(matrix);
            oddReverseCommand.execute(matrix);
            showMatrix.show(matrix);
        } catch (IllegalArgsMatrixException e) {
            e.printStackTrace();
        } catch (IllegalArgGenerationException e) {
            e.printStackTrace();
        }
    }
}
