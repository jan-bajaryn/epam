package by.epam.task13.controller;

import by.epam.task13.controller.command.ReadFileCommand;
import by.epam.task13.entities.Order;
import by.epam.task13.entities.help.Pair;
import by.epam.task13.service.OrdersBuilder;
import by.epam.task13.service.impl.OrdersDomBuilder;
import by.epam.task13.service.impl.OrdersSaxBuilder;
import by.epam.task13.service.impl.OrdersStAXBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "ServletIndex")
public class ServletIndex extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new ReadFileCommand().execute(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        String l = (String) session.getAttribute("locale");
//        if (l == null) {
//            l = "ru";
//        } else if (l.equalsIgnoreCase("ru")) {
//            l = "en";
//        } else {
//            l = "ru";
//        }
//        session.setAttribute("locale", l);
//        req.setAttribute("loc", l);


        Locale l = (Locale) Config.get(session, Config.FMT_LOCALE);

//        String l = (String) session.getAttribute("locale");
        if (l == null) {
            Config.set(session, Config.FMT_LOCALE, new Locale("en", "US"));
        } else if (l.getCountry().equalsIgnoreCase("ru")) {
            Config.set(session, Config.FMT_LOCALE, new Locale("en", "US"));
        } else {
            Config.set(session, Config.FMT_LOCALE, new Locale("ru", "RU"));
        }
//        session.setAttribute("locale", l);
//        req.setAttribute("loc", l);


//        if (locale == null) {
//            locale = new Locale("RU", "ru");
//        } else if (locale.getCountry().equalsIgnoreCase("ru")) {
//            locale = new Locale("en", "US");
//        } else {
//            locale = new Locale("RU", "ru");
//        }
//        session.setAttribute("locale", locale);
//        req.se("index.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath()+"/");
    }
}
