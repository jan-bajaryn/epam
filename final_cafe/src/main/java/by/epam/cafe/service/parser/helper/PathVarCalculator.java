package by.epam.cafe.service.parser.helper;

import by.epam.cafe.service.exception.IllegalPathParamException;

/**
 * Dedicated to parse {@code url} to path variables {@code pathVariable}
 */
public interface PathVarCalculator {
     /**
      * Delimiter for {@code url} from query
      */
     String DELIMITER = "/";

     /**
      * @param path url from query {@code url}
      * @return value of the last part of the url if it possible to parse it to Integer
      * @throws IllegalPathParamException if it isn't possible to parse the {@code url} and find last part
      * Integer, if path equals null, path not contains {@link PathVarCalculator#DELIMITER}
      */
    Integer findLastInteger(String path) throws IllegalPathParamException;
}
