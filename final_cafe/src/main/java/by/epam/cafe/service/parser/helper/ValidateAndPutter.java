package by.epam.cafe.service.parser.helper;

import by.epam.cafe.entity.struct.OptionalNullable;

import java.util.Map;

/**
 * Dedicated to help classes from package
 * {@link by.epam.cafe.service.parser.full}
 * put error and input in response structure
 */
public interface ValidateAndPutter {
    /**
     * @param redirect Map to return what parameter is valid, and value with
     *                 what parameter was in input
     *                 First String in the map is the name of parameter
     *                 Second String in the map is value of input in parameter
     *                 or information about existing error in the map
     *                 For example {street, abcde} means that input for
     *                 parameter of name "street" was "abcde"
     *                 {street_error, true} means that in parameter
     *                 of name "street" was error.
     * @param optional parameter what is empty if input was invalid and not empty otherwise
     * @param label    String value to identify message what should be put in response
     * @param param    Value of parameter from input
     * @return true if optional was not empty, otherwise returns false
     */
    boolean validateAndPut(Map<String, String> redirect, OptionalNullable<?> optional, String label, String param);
}
