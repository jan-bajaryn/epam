package by.epam.task07.twodemesionalarrays.controller;

import by.epam.task07.twodemesionalarrays.service.OddReverse;
import by.epam.task07.twodemesionalarrays.entity.Matrix;

public class OddReverseCommand {
    private OddReverse oddReverse = new OddReverse();

    public void execute(Matrix matrix) {
        oddReverse.reverse(matrix);
    }
}
