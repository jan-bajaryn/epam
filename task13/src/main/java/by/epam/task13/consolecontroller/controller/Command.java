package by.epam.task13.consolecontroller.controller;


import by.epam.task13.consolecontroller.controller.command.dialog.Request;
import by.epam.task13.consolecontroller.controller.command.dialog.Response;

public interface Command {
    Response execute(Request request);
}
