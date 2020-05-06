package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.entity.impl.DeliveryInf;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.full.OrderParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class MakeOrderClient extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(MakeOrderClient.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final OrderParser orderParser = serviceFactory.getOrderParser();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String referer = request.getHeader("referer");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<String, String> redirect = new HashMap<>();

        try {
            Order order = orderService.findCurrentByUserId(user.getId());
            if (order != null) {
                update(request, response, referer, redirect, order);
            } else {
                redirect.put("no_products_error", "true");
                response.sendRedirect(referer);
            }
        } catch (ServiceException e) {
            redirect.put("no_products_error", "true");
            session.setAttribute(REDIRECTED_INFO, redirect);
            response.sendRedirect(referer);
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response, String referer, Map<String, String> redirect, Order order) throws IOException {
        if (buildOrder(request, order, redirect)) {

            try {
                if (orderService.update(order)) {
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "/your-order/" + order.getId());
                } else {
                    request.setAttribute("unknown_error", "true");
                    response.sendRedirect(referer);
                }
            } catch (ServiceException e) {
                request.setAttribute("unknown_error", "true");
                response.sendRedirect(referer);
            }
        } else {
            response.sendRedirect(referer);
            request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        }
    }

    private boolean buildOrder(HttpServletRequest request, Order order, Map<String, String> redirect) {


        String street = request.getParameter("street");
        String comments = request.getParameter("comments");
        String floor = request.getParameter("floor");
        String porch = request.getParameter("porch");
        String room = request.getParameter("room");
        String house = request.getParameter("house");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String time = request.getParameter("time");

        return orderParser.parseWithBase(redirect, order, street, comments, floor, porch, room, house, name, tel, email, time);
    }


}
