package by.epam.cafe.controller.factory;

import by.epam.cafe.controller.command.Command;
import by.epam.cafe.controller.factory.exception.PageNotFoundException;

public interface CommandFactory {
    Command create(String key) throws PageNotFoundException;
}
