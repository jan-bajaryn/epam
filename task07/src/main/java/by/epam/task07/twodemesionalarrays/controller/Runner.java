package by.epam.task07.twodemesionalarrays.controller;


import by.epam.task07.twodemesionalarrays.service.CycleFiller;
import by.epam.task07.twodemesionalarrays.entity.Matrix;
import by.epam.task07.twodemesionalarrays.entity.SquareMatrix;
import by.epam.task07.twodemesionalarrays.factory.exception.IllegalArgsMatrixException;
import by.epam.task07.twodemesionalarrays.factory.exception.IllegalArgsSquareMatrixException;
import by.epam.task07.twodemesionalarrays.factory.MatrixFactory;
import by.epam.task07.twodemesionalarrays.factory.SquareMatrixFactory;
import by.epam.task07.twodemesionalarrays.service.*;
import by.epam.task07.twodemesionalarrays.service.exception.IllegalArgGenerationException;
import by.epam.task07.twodemesionalarrays.service.exception.NullMatrixException;
import by.epam.task07.twodemesionalarrays.service.exception.UnresolvableException;
import by.epam.task07.twodemesionalarrays.validator.MagicSquareValidator;
import by.epam.task07.twodemesionalarrays.view.ConsoleReaderCommand;
import by.epam.task07.twodemesionalarrays.view.IllegalInputReaderException;
import by.epam.task07.twodemesionalarrays.view.ShowMatrix;

public class Runner {
    public static void main(String[] args) {
        ConsoleReaderCommand consoleReaderCommand = new ConsoleReaderCommand();
        ZeroOneFillerCommand zeroOneFillerCommand = new ZeroOneFillerCommand();
        MatrixFactory matrixFactory = new MatrixFactory();
        OddReverseCommand oddReverseCommand = new OddReverseCommand();
        ShowMatrix showMatrix = new ShowMatrix();
        FillRandomlyMatrix fillRandomlyMatrix = new FillRandomlyMatrix();
        CheckerRowsCommand checkerRowsCommand = new CheckerRowsCommand();
        SquareMatrixFactory squareMatrixFactory = new SquareMatrixFactory();
        MagicSquareFiller magicSquareFiller = new MagicSquareFiller();
        MagicSquareValidator magicSquareValidator = new MagicSquareValidator();
        CycleFiller cycleFiller = new CycleFiller();

        boolean isDone = false;
        while (!isDone) {
            switch (consoleReaderCommand.chooseCommand()) {
                case ConsoleReaderCommand.ZERO_ONE_FILL:
                    try {
                        Pair pair = consoleReaderCommand.readParams();
                        zeroOneFillerCommand.execute(pair.getFst(), pair.getSec());
                    } catch (IllegalInputReaderException e) {
                        System.out.println("You need to write integers.");
                    }
                    break;
                case ConsoleReaderCommand.ODD_REVERSE:
                    try {
                        Pair pairSec = consoleReaderCommand.readParams();
                        Pair minMax = consoleReaderCommand.readMinMax();
                        Matrix matrix = matrixFactory.create(pairSec.getFst(), pairSec.getSec());
                        fillRandomlyMatrix.fillRandomly(matrix, minMax.getFst(), minMax.getSec());
                        System.out.println("Matrix before reversing.");
                        showMatrix.show(matrix);
                        oddReverseCommand.execute(matrix);
                        System.out.println("Matrix after reversing.");
                        showMatrix.show(matrix);
                    } catch (IllegalArgsMatrixException e) {
                        System.out.println("Illegal argument matrix.");
                    } catch (IllegalArgGenerationException e) {
                        System.out.println("Illegal arguments minimum or maximum");
                    } catch (IllegalInputReaderException e) {
                        System.out.println("You need to write integers.");

                    }
                    break;
                case ConsoleReaderCommand.ROW_NUMBERS_VALUE:
                    try {
                        Pair pairThird = consoleReaderCommand.readParams();
                        Pair minMaxSec = consoleReaderCommand.readMinMax();
                        Pair checkParams = consoleReaderCommand.readCheckParams();
                        Matrix matrix = matrixFactory.create(pairThird.getFst(), pairThird.getSec());
                        fillRandomlyMatrix.fillRandomly(matrix, minMaxSec.getFst(), minMaxSec.getSec());
                        showMatrix.show(matrix);
                        checkerRowsCommand.execute(matrix, checkParams.getFst(), checkParams.getSec());
                    } catch (IllegalArgGenerationException e) {
                        System.out.println("Illegal arguments for minumum or maximum");
                    } catch (IllegalArgsMatrixException e) {
                        System.out.println("Illegal args for matrix");
                    } catch (IllegalInputReaderException e) {
                        System.out.println("You need to write integers.");
                    }

                    break;
                case ConsoleReaderCommand.MAGIC_SQUARE:
                    try {
                        int i = consoleReaderCommand.readSize();
                        SquareMatrix squareMatrix = squareMatrixFactory.create(i);
                        System.out.println("Matrix before filling.");
                        showMatrix.show(squareMatrix);
                        magicSquareFiller.fill(squareMatrix);
                        System.out.println("Matrix after filling.");
                        showMatrix.show(squareMatrix);
                        System.out.println("Square is magic = " + magicSquareValidator.isValid(squareMatrix));
                    } catch (UnresolvableException e) {
                        System.out.println("Magic matrix can be from 3 rows size.");
                    } catch (IllegalArgsSquareMatrixException e) {
                        System.out.println("Wrong params to square matrix.");
                    } catch (IllegalInputReaderException e) {
                        System.out.println("You need to write integers.");
                    }
                    break;
                case ConsoleReaderCommand.CYCLE_FILL:
                    try {
                        int i = consoleReaderCommand.readSize();
                        SquareMatrix squareMatrix = squareMatrixFactory.create(i);
                        int beginValue = consoleReaderCommand.readBeginValue();
                        System.out.println("Matrix before filling.");
                        showMatrix.show(squareMatrix);
                        cycleFiller.fillCycle(squareMatrix, beginValue);
                        System.out.println("Matrix after filling.");
                        showMatrix.show(squareMatrix);
                    } catch (NullMatrixException e) {
                        System.out.println("Matrix can't be null.");
                    } catch (IllegalArgsSquareMatrixException e) {
                        System.out.println("Arguments for square matrix are incorrect.");
                    } catch (IllegalInputReaderException e) {
                        System.out.println("You need to write integers.");
                    }
                    break;
                case ConsoleReaderCommand.EXIT:
                    isDone = true;
                    break;
                default:
                    System.out.println("Wrong input.");
            }
        }

    }
}
