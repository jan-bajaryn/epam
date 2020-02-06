package by.epam.task11.controller;


import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;

public interface Command {
    Response execute(Request request);
}
