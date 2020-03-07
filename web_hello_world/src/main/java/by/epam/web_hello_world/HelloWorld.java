package by.epam.web_hello_world;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/hello")
public class HelloWorld extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("cde");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");


        out.println("<ul>");
        for (String s : list) {
            out.println("<li>" + s + "</li>");
        }
        out.println("</ul>");

        out.println("</body>");
        out.println("</html>");

        response.setContentType("text/html");
        ArrayList<Object> o = new ArrayList<>();
        o.add("abc");
        o.add("cde");
        request.setAttribute("strings", o);
        request.getRequestDispatcher("/WEB-INF/jsp/list").forward(request, response);
    }
}
