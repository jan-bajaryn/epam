package by.epam.task7.twodemesionalarrays.service;

import by.epam.task7.twodemesionalarrays.entity.Matrix;

import java.util.Random;

public class FillRandomlyMatrix{
    Random random = new Random();

    public void fillRandomly(Matrix matrix, int min, int max) throws IllegalArgGenerationException {

        if (min > max) {
            throw new IllegalArgGenerationException();
        }

        for (int i = 0; i < matrix.calcRows(); i++) {
            for (int j = 0; j < matrix.calcColumns(); j++) {
                matrix.setElement(i, j, random.nextInt(max - min) + min);
            }
        }
    }
}
