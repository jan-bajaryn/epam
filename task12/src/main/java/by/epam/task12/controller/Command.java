package by.epam.task12.controller;


import by.epam.task12.controller.command.dialog.Request;
import by.epam.task12.controller.command.dialog.Response;

public interface Command {
    Response execute(Request request);
}
