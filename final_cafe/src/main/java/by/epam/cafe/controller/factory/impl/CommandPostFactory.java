package by.epam.cafe.controller.factory.impl;

import by.epam.cafe.controller.command.Command;
import by.epam.cafe.controller.command.postimpl.LoginCommand;
import by.epam.cafe.controller.factory.CommandFactory;
import by.epam.cafe.controller.factory.exception.PageNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandPostFactory implements CommandFactory {

    private CommandPostFactory() {
    }

    private static final CommandPostFactory instance = new CommandPostFactory();

    public static CommandPostFactory getInstance() {
        return instance;
    }

    private static final Logger log = LogManager.getLogger(CommandPostFactory.class);


    private static Map<String, Command> commandMap = new LinkedHashMap<>();

    static {
        /*language=RegExp*/
        commandMap.put("/login", new LoginCommand());
    }

    @Override
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