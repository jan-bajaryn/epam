package by.epam.cafe.controller.factory.impl;

import by.epam.cafe.controller.command.Command;
import by.epam.cafe.controller.command.CommandDecorator;
import by.epam.cafe.controller.command.postimpl.*;
import by.epam.cafe.controller.factory.CommandFactory;
import by.epam.cafe.controller.factory.exception.PageNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static by.epam.cafe.entity.enums.Role.ADMIN;
import static by.epam.cafe.entity.enums.Role.ANON;

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
        commandMap.put("/login", new CommandDecorator(new LoginCommand(), EnumSet.of(ANON)));
        commandMap.put("/admin/create_user", new CommandDecorator(new CreateUserCommand(), EnumSet.of(ADMIN)));
        commandMap.put("/change-language", new ChangeLanguageCommand());
        commandMap.put("/registration", new RegistrationCommand());
        commandMap.put("/admin/edit_user", new EditAdminCommand());
        /*language=RegExp*/
        commandMap.put("/admin/block/\\d+", new UserBlockCommand());
        /*language=RegExp*/
        commandMap.put("/admin/unblock/\\d+", new UserUnBlockCommand());
        commandMap.put("/admin/edit-product", new EditProductCommand());
        commandMap.put("/admin/create-product", new CreateProductCommand());
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
