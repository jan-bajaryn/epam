package by.epam.cafe.service.parser.helper.impl;

import by.epam.cafe.service.exception.IllegalPathParamException;
import by.epam.cafe.service.parser.helper.PathVarCalculator;

public class PathVarCalculatorImpl implements PathVarCalculator {
    private static PathVarCalculatorImpl INSTANCE = new PathVarCalculatorImpl();

    public static PathVarCalculatorImpl getInstance() {
        return INSTANCE;
    }

    private PathVarCalculatorImpl() {
    }

    /**
     * @param path url from query {@code url}
     * @return value of the last part of the url if it possible to parse it to Integer
     * @throws IllegalPathParamException if it isn't possible to parse the {@code url} and find last part
     *                                   Integer, if path equals null, path not contains {@link PathVarCalculator#DELIMITER}
     */
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
