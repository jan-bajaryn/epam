package by.epam.cafe.controller;

import by.epam.cafe.controller.command.Command;
import by.epam.cafe.controller.factory.CommandGetFactory;
import by.epam.cafe.controller.factory.PageNotFoundException;
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

    private CommandGetFactory commandGetFactory = new CommandGetFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        getFactory.create(request.get);
//        log.info("request.getContextPath() = {}", request.getContextPath());
//        log.info("request.getRequestURI() = {}", request.getRequestURI());
//        System.out.println("request.getContextPath() = " + request.getContextPath());
//        System.out.println("request.getRequestURI() = " + request.getRequestURI());
//        log.info("request.getPathInfo() = {}", request.getPathInfo());
//        log.info("request.getRequestURL().toString() = {}", request.getRequestURL().toString());
//        log.info("request.getLocalName() = {}", request.getLocalName());
//
//        log.info("request.getServletPath() = {}", request.getServletPath());

        try {
            String requestURI = request.getRequestURI();
            log.info("requestURI = {}", requestURI);
            String prefix = request.getContextPath() + request.getServletPath();
            log.info("prefix = {}", prefix);
            Command command = commandGetFactory.create(requestURI.substring(prefix.length()));
            command.execute(request, response);
        } catch (PageNotFoundException e) {
            log.info("e.getMessage() = {}", e.getMessage());
        }
//        request.getRequestDispatcher("/WEB-INF/jsp/page.jsp").forward(request, response);
    }
}
