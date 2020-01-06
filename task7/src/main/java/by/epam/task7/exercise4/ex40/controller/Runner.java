package by.epam.task7.exercise4.ex40.controller;

import by.epam.task7.exercise4.ex40.entity.SquareMatrix;
import by.epam.task7.exercise4.ex40.factory.SquareMatrixFactory;
import by.epam.task7.exercise4.ex40.service.MagicSquareFiller;
import by.epam.task7.exercise4.ex40.service.UnresolvableException;
import by.epam.task7.exercise4.ex40.validator.MagicSquareValidator;
import by.epam.task7.twodemesionalarrays.factory.IllegalArgsMatrixException;
import by.epam.task7.twodemesionalarrays.service.FillWithIntegerMatrix;
import by.epam.task7.twodemesionalarrays.view.ShowMatrix;

public class Runner {
    public static void main(String[] args) {
        SquareMatrixFactory squareMatrixFactory = new SquareMatrixFactory();
        MagicSquareFiller magicSquareFiller = new MagicSquareFiller();
        ShowMatrix showMatrix = new ShowMatrix();
        MagicSquareValidator magicSquareValidator = new MagicSquareValidator();
        try {
            SquareMatrix squareMatrix = squareMatrixFactory.create(10);
            showMatrix.show(squareMatrix);
            magicSquareFiller.fill(squareMatrix);
//            magicSquareFiller.fillOddSquare(squareMatrix,0,0,4,4,2);
            showMatrix.show(squareMatrix);
            System.out.println("Matrix is magic - " + magicSquareValidator.isValid(squareMatrix));
        } catch (IllegalArgsMatrixException | UnresolvableException e) {
            e.printStackTrace();
        }
    }
}
