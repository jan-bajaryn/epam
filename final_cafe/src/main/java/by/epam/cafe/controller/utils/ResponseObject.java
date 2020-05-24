package by.epam.cafe.controller.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ResponseObject {
    void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
