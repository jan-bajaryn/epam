package by.epam.task7.cyclefiller.controller;

import by.epam.task7.cyclefiller.service.CycleFiller;
import by.epam.task7.exercise4.ex40.entity.SquareMatrix;
import by.epam.task7.exercise4.ex40.factory.IllegalArgsSquareMatrixException;
import by.epam.task7.exercise4.ex40.factory.SquareMatrixFactory;
import by.epam.task7.twodemesionalarrays.service.FillRandomlyMatrix;
import by.epam.task7.twodemesionalarrays.view.ShowMatrix;

public class Runner {
    public static void main(String[] args) {
        CycleFiller cycleFiller = new CycleFiller();
        FillRandomlyMatrix fillRandomlyMatrix = new FillRandomlyMatrix();
        SquareMatrixFactory squareMatrixFactory = new SquareMatrixFactory();
        ShowMatrix showMatrix = new ShowMatrix();
        try {
            SquareMatrix squareMatrix = squareMatrixFactory.create(6);
            cycleFiller.fillCycle(squareMatrix,1);
            showMatrix.show(squareMatrix);
        } catch (IllegalArgsSquareMatrixException e) {
            e.printStackTrace();
        }
    }
}
