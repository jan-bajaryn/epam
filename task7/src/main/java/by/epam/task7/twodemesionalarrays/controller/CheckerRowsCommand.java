package by.epam.task7.exercise3.ex30.controller;

import by.epam.task7.exercise3.ex30.service.RowsSubmit;
import by.epam.task7.twodemesionalarrays.entity.Matrix;

import java.util.List;

public class CheckerRowsCommand {
    private RowsSubmit rowsSubmit = new RowsSubmit();

    public void execute(Matrix matrix, int value, int number) {
        List<Integer> list = rowsSubmit.check(matrix, value, number);
        System.out.println("Numbers of rows where value = " + value + " there " + number + " and more times are: " + list);
    }
}
