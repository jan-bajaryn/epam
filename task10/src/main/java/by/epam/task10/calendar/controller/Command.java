package by.epam.task10.calendar.controller;

import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;

public interface Command {
    Response execute(Request request);
}
