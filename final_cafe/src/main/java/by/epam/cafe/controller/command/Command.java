package by.epam.cafe.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException;
}
