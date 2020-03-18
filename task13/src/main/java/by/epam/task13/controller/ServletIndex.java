package by.epam.task13.controller;

import by.epam.task13.controller.command.ChangeLanguageCommand;
import by.epam.task13.controller.command.ReadFileCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletIndex")
public class ServletIndex extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new ReadFileCommand().execute(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new ChangeLanguageCommand().execute(req, resp);
    }

}
