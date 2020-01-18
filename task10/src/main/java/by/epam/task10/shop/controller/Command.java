package by.epam.task10.shop.controller;


import by.epam.task10.shop.entity.dialog.Request;
import by.epam.task10.shop.entity.dialog.Response;

public interface Command {
    Response execute(Request request);
}
