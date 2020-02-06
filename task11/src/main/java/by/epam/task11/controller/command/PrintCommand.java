package by.epam.task11.controller.command;

import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;

public class PrintCommand implements by.epam.task11.controller.ExecCommand {
    @Override
    public Response execute(Request request) {
        return null;
    }

    @Override
    public String definition() {
        return "Print existing text to console.";
    }
}
