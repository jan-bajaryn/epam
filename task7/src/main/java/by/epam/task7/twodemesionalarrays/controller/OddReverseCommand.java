package by.epam.task7.twodemesionalarrays.controller;

import by.epam.task7.twodemesionalarrays.service.OddReverse;
import by.epam.task7.twodemesionalarrays.entity.Matrix;

public class OddReverseCommand {
    private OddReverse oddReverse = new OddReverse();

    public void execute(Matrix matrix) {
        oddReverse.reverse(matrix);
    }
}
