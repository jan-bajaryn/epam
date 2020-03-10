package by.epam.task13.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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
import java.util.List;

@WebServlet(name = "ServletTest", urlPatterns = "/test")
public class ServletTest extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ServletTest.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "";
        String filePath = "";

        downloadFile(request);
        request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request, response);

    }

    private String downloadFile(HttpServletRequest request) {
        String name;
        String filePath;
        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(request);

            for (FileItem item : multiFiles) {
                if (item.isFormField()) {
                    name = item.getString();
                    log.info("is form field");
                } else {
                    File file = new File("src/main/resources/something/" + item.getName());
                    item.write(file);
//                    filePath = file.getPath();
                    log.info("is not form field");
                    return file.getPath();
                }
            }
//            request.setAttribute("a", true);
        } catch (Exception e) {
            log.info("doPost: e.getMessage() = {}", e.getMessage());
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request, response);
    }
}
