package by.epam.task12.service.factory;

import by.epam.task12.entity.Matrix;
import by.epam.task12.service.CommonCounter;

public class CommonCounterFactory {

    public CommonCounter create(Matrix matrix) {
        return new CommonCounter(Math.min(matrix.calcColumns(), matrix.calcRows()));
    }

}
