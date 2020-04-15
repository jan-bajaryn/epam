package by.epam.cafe.service.parser;

public interface NullIfEmptyService {

    String nullIfEmptyString(String param);

    /**
     * @param param parameter to parse
     * @return Integer value of param if it can be parse to Integer. Else throws NumberFormatException
     * @throws NumberFormatException if param can't be parse to Integer
     */
    Integer nullIfEmptyInteger(String param);
}
