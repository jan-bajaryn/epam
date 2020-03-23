package by.epam.cafe.service.parser;

import by.epam.cafe.service.exception.IllegalPathParamException;

public interface PathVarCalculator {
     Integer findLastInteger(String path) throws IllegalPathParamException;
}
