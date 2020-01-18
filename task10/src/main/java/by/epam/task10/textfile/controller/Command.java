package by.epam.task10.textfile.controller;


import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;

public interface Command {
    Response execute(Request request);
}
