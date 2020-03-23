package by.epam.cafe.controller.factory;

import by.epam.cafe.controller.command.Command;
import by.epam.cafe.controller.command.getimpl.*;
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
        commandMap.put("/?", new IndexCommand());
        commandMap.put("/order", new OrderCommand());
        commandMap.put("/login", new LoginCommand());
        commandMap.put("/cabinet", new ClientCabinetCommand());
        /*language=RegExp*/
        commandMap.put("/edit-order/\\d+", new EditOrderCommand());
        commandMap.put("/order-list", new OrderListCommand());
        commandMap.put("/registration", new RegistrationCommand());
        /*language=RegExp*/
        commandMap.put("/your-order/\\d+", new YourOrderCommand());


        commandMap.put("/admin/create-product", new CreateProductCommand());
        commandMap.put("/admin/create-product-group", new CreateProductGroupCommand());
        commandMap.put("/admin/create-user", new CreateUserCommand());

        commandMap.put("/admin/user-list", new UserListCommand());
        commandMap.put("/admin/product-list", new ProductListCommand());
        commandMap.put("/admin/product-group-list", new ProductGroupListCommand());

        /*language=RegExp*/
        commandMap.put("/admin/edit-user/\\d+", new EditUserCommand());
        /*language=RegExp*/
        commandMap.put("/admin/edit-product-group/\\d+", new EditProductGroupCommand());
        /*language=RegExp*/
        commandMap.put("/admin/edit-product/\\d+", new EditProductCommand());


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
