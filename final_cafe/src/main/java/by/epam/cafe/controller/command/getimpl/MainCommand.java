package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.command.Command;
import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainCommand extends Command {
    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new Forward("/sec.jsp");
    }
}
