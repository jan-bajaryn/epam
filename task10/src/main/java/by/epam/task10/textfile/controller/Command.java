package by.epam.task10.textfile.controller;


import by.epam.task10.textfile.controller.command.dialog.Request;
import by.epam.task10.textfile.controller.command.dialog.Response;

public interface Command {
    Response execute(Request request);
}
