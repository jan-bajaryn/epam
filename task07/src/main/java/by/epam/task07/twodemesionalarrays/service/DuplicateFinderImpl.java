package by.epam.task07.twodemesionalarrays.service;

import by.epam.task07.twodemesionalarrays.entity.SquareMatrix;
import by.epam.task07.twodemesionalarrays.entity.Matrix;

import java.util.ArrayList;
import java.util.List;

public class DuplicateFinderImpl {
    //less memory, but after 400 is very slow
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

    //more fast but use more memory
    public boolean isDuplicatedMagicSquare(SquareMatrix matrix) {
        int[] arr = new int[matrix.calcRows() * matrix.calcColumns() + 1];
        for (int i = 0; i < matrix.calcRows(); i++) {
            for (int j = 0; j < matrix.calcColumns(); j++) {
                int current = matrix.calcElem(i, j);
                if (arr[current] != 0) {
                    System.out.println("I'm returning true - duplication");
                    return true;
                }
                arr[current] = current;
            }
        }
        return false;
    }

}
