package by.epam.cafe.controller.factory;

import by.epam.cafe.controller.command.Command;
import by.epam.cafe.controller.command.SecondCommand;
import by.epam.cafe.controller.command.getimpl.MainCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandGetFactory {

    private static final Logger log = LogManager.getLogger(CommandGetFactory.class);

    private static Map<String, Command> commandMap = new LinkedHashMap<>();

    static {
        commandMap.put("/second", new MainCommand());
        /*language=RegExp*/
        commandMap.put("/?", new SecondCommand());
    }

    public Command create(String key) throws PageNotFoundException {
        for (Map.Entry<String, Command> s : commandMap.entrySet()) {
            log.info("s.getKey() = {}", s.getKey());
            log.info("s.getValue() = {}", s.getValue());
            log.info("key = {}", key);
            if (Pattern.compile(s.getKey()).matcher(key).matches()) {
                return s.getValue();
            }
        }
        throw new PageNotFoundException("Page not found.");
    }

}
