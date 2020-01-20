package by.epam.task10.shop.controller;


import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;

public interface Command {
    Response execute(Request request);
}
