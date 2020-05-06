package by.epam.cafe.service.parser.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingEnumeration;
import java.util.Map;
import java.util.Optional;

public class ValidateAndPutter {

    private static final Logger log = LogManager.getLogger(ValidateAndPutter.class);
    public static final String POSTFIX = "_error";


    public static final ValidateAndPutter INSTANCE = new ValidateAndPutter();

    public static ValidateAndPutter getInstance() {
        return INSTANCE;
    }

    private ValidateAndPutter() {
    }

    public boolean validateAndPut(Map<String, String> redirect, Optional<?> optional, String label, String param) {
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
