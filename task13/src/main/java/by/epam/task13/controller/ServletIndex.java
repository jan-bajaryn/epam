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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletIndex")
public class ServletIndex extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new ReadFileCommand().execute(request, response);
    }


}
