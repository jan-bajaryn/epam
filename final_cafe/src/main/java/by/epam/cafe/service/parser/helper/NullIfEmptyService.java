package by.epam.cafe.service.parser.helper;

/**
 * Dedicated to return null if input is empty for different types
 */
public interface NullIfEmptyService {

    /**
     * @param param parameter to parse
     * @return String value of input
     * if input is empty, returns null
     */
    String nullIfEmptyString(String param);

    /**
     * @param param parameter to parse
     * @return Integer value of param if it can be parse to Integer.
     * Or if input is empty returns null
     * @throws NumberFormatException if param can't be parse to Integer and it isn't empty
     */
    Integer nullIfEmptyInteger(String param);
}
