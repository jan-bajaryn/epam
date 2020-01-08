package by.epam.task7.exercise2.ex11.controller;

import by.epam.task7.exercise2.ex11.service.OddReverse;
import by.epam.task7.twodemesionalarrays.entity.Matrix;

public class OddReverseCommand {
    private OddReverse oddReverse = new OddReverse();

    public void execute(Matrix matrix) {
        oddReverse.reverse(matrix);
    }
}
