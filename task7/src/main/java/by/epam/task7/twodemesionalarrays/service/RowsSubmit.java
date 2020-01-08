package by.epam.task7.exercise3.ex30.service;

import by.epam.task7.twodemesionalarrays.entity.Matrix;

import java.util.ArrayList;
import java.util.List;

public class RowsSubmit {
    public List<Integer> check(Matrix matrix, int value, int number) {
        List<Integer> list = new ArrayList();

        for (int i = 0; i < matrix.calcRows(); i++) {
            int counter = 0;
            for (int j = 0; j < matrix.calcColumns(); j++) {
                if (counter >= number) {
                    list.add(i);
                    break;
                }
                if (matrix.calcElem(i, j) == value) {
                    counter++;
                }
            }

        }
        return list;
    }
}
