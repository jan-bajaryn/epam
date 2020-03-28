package by.epam.cafe.controller;

import by.epam.cafe.controller.command.Command;
import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.controller.factory.CommandFactory;
import by.epam.cafe.controller.factory.impl.CommandGetFactory;
import by.epam.cafe.controller.factory.impl.CommandPostFactory;
import by.epam.cafe.controller.factory.exception.PageNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletCafe")
public class ServletCafe extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ServletCafe.class);

    private final CommandGetFactory commandGetFactory = CommandGetFactory.getInstance();
    private final CommandPostFactory commandPostFactory = CommandPostFactory.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeMethod(commandPostFactory, request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeMethod(commandGetFactory, request, response);
    }

    private void executeMethod(CommandFactory commandFactory,
                               HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        try {
            String requestURI = request.getRequestURI();
            log.info("requestURI = {}", requestURI);
            String prefix = request.getContextPath() + request.getServletPath();
            log.info("prefix = {}", prefix);
            Command command = commandFactory.create(requestURI.substring(prefix.length()));
            command.execute(request, response);
        } catch (PageNotFoundException | PermissionDeniedException e) {
            log.info("e: ", e);
        }
    }

}
