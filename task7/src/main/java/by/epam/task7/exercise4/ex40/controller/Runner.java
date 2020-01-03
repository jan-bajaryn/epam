package by.epam.task7.exercise4.ex40.controller;

import by.epam.task7.exercise4.ex40.entity.SquareMatrix;
import by.epam.task7.exercise4.ex40.factory.SquareMatrixFactory;
import by.epam.task7.exercise4.ex40.service.MagicSquareFiller;
import by.epam.task7.twodemesionalarrays.factory.IllegalArgsMatrixException;
import by.epam.task7.twodemesionalarrays.view.ShowMatrix;

public class Runner {
    public static void main(String[] args) {
        SquareMatrixFactory squareMatrixFactory = new SquareMatrixFactory();
        MagicSquareFiller magicSquareFiller = new MagicSquareFiller();
        ShowMatrix showMatrix = new ShowMatrix();
        try {
            SquareMatrix squareMatrix = squareMatrixFactory.create(4);
            showMatrix.show(squareMatrix);
            magicSquareFiller.fill(squareMatrix);
            showMatrix.show(squareMatrix);
        } catch (IllegalArgsMatrixException e) {
            e.printStackTrace();
        }
    }
}
