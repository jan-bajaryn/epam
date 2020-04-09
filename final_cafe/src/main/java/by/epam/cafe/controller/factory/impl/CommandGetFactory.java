package by.epam.cafe.controller.factory.impl;

import by.epam.cafe.controller.command.Command;
import by.epam.cafe.controller.command.CommandDecorator;
import by.epam.cafe.controller.command.getimpl.*;
import by.epam.cafe.controller.factory.CommandFactory;
import by.epam.cafe.controller.factory.exception.PageNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static by.epam.cafe.entity.enums.Role.*;

public class CommandGetFactory implements CommandFactory {

    private CommandGetFactory() {
    }

    private static CommandGetFactory instance = new CommandGetFactory();

    public static CommandGetFactory getInstance() {
        return instance;
    }

    private static final Logger log = LogManager.getLogger(CommandGetFactory.class);

    private static Map<String, Command> commandMap = new LinkedHashMap<>();

    static {
        commandMap.put("/second", new MainCommand());
        commandMap.put("/something_went_wrong", new SomethingWentWrongCommand());
        /*language=RegExp*/
        commandMap.put("/?", new IndexCommand());
        commandMap.put("/order", new OrderCommand());
        commandMap.put("/login", new CommandDecorator(new LoginCommand(), EnumSet.of(ANON)));
        commandMap.put("/cabinet", new CommandDecorator(new ClientCabinetCommand(), EnumSet.of(CLIENT)));
        /*language=RegExp*/
        commandMap.put("/edit-order/\\d+", new CommandDecorator(new EditOrderCommand(), EnumSet.of(OPERATOR)));
        commandMap.put("/order-list", new CommandDecorator(new OrderListCommand(), EnumSet.of(OPERATOR)));
        commandMap.put("/registration", new RegistrationCommand());
        /*language=RegExp*/
        commandMap.put("/your-order/\\d+", new YourOrderCommand());


        commandMap.put("/admin/create-product", new CommandDecorator(new CreateProductCommand(), EnumSet.of(ADMIN)));
        commandMap.put("/admin/create-product-group", new CommandDecorator(new CreateProductGroupCommand(), EnumSet.of(ADMIN)));
        commandMap.put("/admin/create-user", new CommandDecorator(new CreateUserCommand(), EnumSet.of(ADMIN)));

        commandMap.put("/admin/user-list", new CommandDecorator(new UserListCommand(), EnumSet.of(ADMIN)));
        commandMap.put("/admin/product-list", new CommandDecorator(new ProductListCommand(), EnumSet.of(ADMIN)));
        commandMap.put("/admin/product-group-list", new CommandDecorator(new ProductGroupListCommand(), EnumSet.of(ADMIN)));

        /*language=RegExp*/
        commandMap.put("/admin/edit-user/\\d+", new CommandDecorator(new EditUserCommand(), EnumSet.of(ADMIN)));
        /*language=RegExp*/
        commandMap.put("/admin/edit-product-group/\\d+", new CommandDecorator(new EditProductGroupCommand(), EnumSet.of(ADMIN)));
        /*language=RegExp*/
        commandMap.put("/admin/edit-product/\\d+", new CommandDecorator(new EditProductCommand(), EnumSet.of(ADMIN)));
        commandMap.put("/logout", new CommandDecorator(new LogOutCommand(), EnumSet.complementOf(EnumSet.of(ANON))));
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
