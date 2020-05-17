package by.epam.cafe.service.parser.helper.impl;

import by.epam.cafe.service.exception.IllegalPathParamException;
import by.epam.cafe.service.parser.helper.PathVarCalculator;

public class PathVarCalculatorImpl implements PathVarCalculator {

    @Override
    public Integer findLastInteger(String path) throws IllegalPathParamException {
        if (path == null || !path.contains(DELIMITER)) {
            throw new IllegalPathParamException("Path doesn't contains delimiter character.");
        }

        try {
            String stVar = path.substring(path.lastIndexOf(DELIMITER) + 1);
            return Integer.valueOf(stVar);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalPathParamException("Problem in parsing the string.", e);
        }

    }
}
