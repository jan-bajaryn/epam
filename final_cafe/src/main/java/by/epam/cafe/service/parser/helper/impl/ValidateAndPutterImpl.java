package by.epam.cafe.service.parser.helper.impl;

import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.service.parser.helper.ValidateAndPutter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ValidateAndPutterImpl implements by.epam.cafe.service.parser.helper.ValidateAndPutter {

    private static final Logger log = LogManager.getLogger(ValidateAndPutterImpl.class);
    public static final String POSTFIX = "_error";


    public static final ValidateAndPutter INSTANCE = new ValidateAndPutterImpl();

    public static ValidateAndPutter getInstance() {
        return INSTANCE;
    }

    private ValidateAndPutterImpl() {
    }

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
    @Override
    public boolean validateAndPut(Map<String, String> redirect, OptionalNullable<?> optional, String label, String param) {
        redirect.put(label, param);
        if (optional.isEmpty()) {
            redirect.put(label + POSTFIX, "true");
            return false;
        }
        log.debug("label = {}", label);
        log.debug("param = {}", param);
        return true;
    }
}
