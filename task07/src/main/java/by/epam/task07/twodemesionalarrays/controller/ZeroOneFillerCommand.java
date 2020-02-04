package by.epam.task07.twodemesionalarrays.controller;

import by.epam.task07.twodemesionalarrays.service.exception.NullMatrixException;
import by.epam.task07.twodemesionalarrays.service.SingleMultValuesFiller;
import by.epam.task07.twodemesionalarrays.entity.Matrix;
import by.epam.task07.twodemesionalarrays.factory.exception.IllegalArgsMatrixException;
import by.epam.task07.twodemesionalarrays.factory.MatrixFactory;
import by.epam.task07.twodemesionalarrays.view.ShowMatrix;

public class ZeroOneFillerCommand {

    private MatrixFactory matrixFactory = new MatrixFactory();
    private SingleMultValuesFiller singleMultValuesFiller = new SingleMultValuesFiller();
    private ShowMatrix showMatrix = new ShowMatrix();

    public void execute(int rows, int columns) {
        try {
            Matrix matrix = matrixFactory.create(rows, columns);
            singleMultValuesFiller.fill(matrix, 1, 0);
            showMatrix.show(matrix);
        } catch (IllegalArgsMatrixException e) {
            System.out.println("Illegal matrix params. No changes.");
        } catch (NullMatrixException e) {
            System.out.println("Wrong parameters to matrix. Matrix is null.");
        }
    }
}
