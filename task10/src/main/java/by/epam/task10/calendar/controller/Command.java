package by.epam.task10.calendar.controller;

import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;

public interface Command {
    Response execute(Request request);
}
