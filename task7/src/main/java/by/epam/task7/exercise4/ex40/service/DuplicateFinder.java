package by.epam.task7.exercise4.ex40.service;

import by.epam.task7.twodemesionalarrays.entity.Matrix;

import java.util.ArrayList;
import java.util.List;

public class DuplicateFinder {
    public boolean isDuplicatedValues(Matrix matrix) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < matrix.calcRows(); i++) {
            for (int j = 0; j < matrix.calcColumns(); j++) {
                int current = matrix.calcElem(i, j);
                if (list.contains(current)) {
                    System.out.println("I'm returning true - duplication");
                    return true;
                }
                list.add(current);
            }
        }
        return false;
    }
}
