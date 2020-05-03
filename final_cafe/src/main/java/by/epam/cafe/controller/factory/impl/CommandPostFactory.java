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
        commandMap.put("/login", new CommandDecorator(new Login(), EnumSet.of(ANON)));
        commandMap.put("/admin/create_user", new CommandDecorator(new CreateUser(), EnumSet.of(ADMIN)));
        commandMap.put("/change-language", new ChangeLanguage());
        commandMap.put("/registration", new Registration());
        commandMap.put("/admin/edit_user", new EditAdmin());
        /*language=RegExp*/
        commandMap.put("/admin/block/\\d+", new UserBlock());
        /*language=RegExp*/
        commandMap.put("/admin/unblock/\\d+", new UserUnBlock());
        commandMap.put("/admin/edit-product", new EditProduct());
        commandMap.put("/admin/create-product", new CreateProduct());

        commandMap.put("/admin/create-product-group", new CreateProductGroup());
        commandMap.put("/admin/edit-product-group", new EditProductGroup());

        commandMap.put("/admin/disable-product-group", new DisableProductGroup());
        commandMap.put("/admin/enable-product-group", new EnableProductGroup());

        commandMap.put("/put-item-anon", new PutItemAnon());
        commandMap.put("/minus-item-anon", new MinusItemAnon());

        commandMap.put("/delete-all-anon", new DeleteAllProdFromBasket());

        commandMap.put("/make-order-anon", new MakeOrderAnon());
        commandMap.put("/cancel_order", new CancelOrder());

        commandMap.put("/edit-order", new EditOrder());

        commandMap.put("/operator/plus-product", new PlusProductOperator());

        commandMap.put("/operator/minus-product", new MinusProductOperator());
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
