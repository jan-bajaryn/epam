package by.epam.task12.controller;

import by.epam.task12.entity.impl.MatrixImpl;
import by.epam.task12.service.FillWithIntegerMatrix;
import by.epam.task12.service.ThreadDiagonalFiller;
import by.epam.task12.service.factory.MatrixFactory;
import by.epam.task12.service.factory.exception.IllegalArgsMatrixException;
import by.epam.task12.service.validator.DiagonalZeroChecker;
import by.epam.task12.view.ShowMatrix;

public class Runner {
    private ThreadDiagonalFiller threadDiagonalFiller = new ThreadDiagonalFiller();

    public static void main(String[] args) throws IllegalArgsMatrixException {
        MatrixFactory matrixFactory = new MatrixFactory();
        FillWithIntegerMatrix fillWithIntegerMatrix = new FillWithIntegerMatrix();
        DiagonalZeroChecker diagonalZeroChecker = new DiagonalZeroChecker();
        ShowMatrix showMatrix = new ShowMatrix();
        ThreadDiagonalFiller threadDiagonalFiller = new ThreadDiagonalFiller();


        MatrixImpl matrixImpl = matrixFactory.create(8, 8);
        showMatrix.show(matrixImpl);
        System.out.println(diagonalZeroChecker.isValid(matrixImpl));

//        fillWithIntegerMatrix.fillWithInteger(matrix, -1);
//        showMatrix.show(matrix);
//        System.out.println(diagonalZeroChecker.isValid(matrix));

        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println(coreCount);

        int[] arr = {1,2,3,4,5,6};
        threadDiagonalFiller.fill(matrixImpl,arr);
        showMatrix.show(matrixImpl);

    }

}
