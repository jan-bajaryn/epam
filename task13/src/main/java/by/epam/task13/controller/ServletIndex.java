package by.epam.task13.controller;

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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ServletIndex")
public class ServletIndex extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ServletIndex.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Pair<String, String> pair = downloadFile(request);

        List<Order> orders;

        log.info("doPost: type = {}", pair.getFst());
        log.info("doPost: path = {}", pair.getSec());

        if (checkNulls(request, response, pair.getFst(), pair.getSec())) {
            return;
        }

        orders = chooseAndExecute(pair.getFst(), pair.getSec());

        deleteFile(pair.getSec());

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/jsp/table.jsp").forward(request, response);

    }

    private void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    private boolean checkNulls(HttpServletRequest request, HttpServletResponse response, String type, String path) throws ServletException, IOException {
        if (type == null || path == null) {
            request.setAttribute("failed", true);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return true;
        }
        return false;
    }

    private List<Order> chooseAndExecute(String type, String path) {
        List<Order> orders;
        if (type.equalsIgnoreCase("sax")) {
            OrdersBuilder builder = new OrdersSaxBuilder();
//            builder.buildListOrders("src/main/resources/orders.xml");
            builder.buildListOrders(path);
            orders = builder.getOrders();
        } else if (type.equalsIgnoreCase("stax")) {
            OrdersBuilder builder = new OrdersStAXBuilder();
            builder.buildListOrders(path);
            orders = builder.getOrders();
        } else if (type.equalsIgnoreCase("dom")) {
            OrdersBuilder builder = new OrdersDomBuilder();
            builder.buildListOrders(path);
            orders = builder.getOrders();
        } else {
            orders = new ArrayList<>();
        }
        return orders;
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//
//    }

    private Pair<String, String> downloadFile(HttpServletRequest request) {
        Pair<String, String> pair = new Pair<>();

        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());

            List<FileItem> parts = fileUpload.parseRequest(request);

            for (FileItem part : parts) {
                if (!part.isFormField()) {
                    String pathFromFile = "src/main/resources/something/" + part.getName();
                    File file = new File(pathFromFile);
                    part.write(file);
                    pair.setSec(pathFromFile);

                    log.info("downloadFile: pathFromFile = {}", pathFromFile);
                } else {
                    pair.setFst(part.getString());
                    log.info("downloadFile: pair.getFst() = {}", pair.getFst());

                }
            }
        } catch (Exception e) {
            log.info("doPost: e.getMessage() = {}", e.getMessage());
        }
        return pair;
    }

}