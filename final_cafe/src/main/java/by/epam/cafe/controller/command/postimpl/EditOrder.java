package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.entity.impl.DeliveryInf;
import by.epam.cafe.entity.impl.Order;
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

public class EditOrder extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(EditOrder.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final OrderParser orderParser = serviceFactory.getOrderParser();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String referer = request.getHeader("referer");

        HttpSession session = request.getSession();
        Map<String, String> redirect = new HashMap<>();

        try {
            String id = request.getParameter("id");
            Order order = orderService.findEntityById(Integer.valueOf(id));
            if (order != null) {
                update(request, response, referer, redirect, order);
            } else {
                redirect.put("fatal_id", "true");
                session.setAttribute(REDIRECTED_INFO, redirect);
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/order-list?pagination=1");
            }
        } catch (ServiceException e) {
            redirect.put("fatal_id", "true");
            session.setAttribute(REDIRECTED_INFO, redirect);
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/order-list?pagination=1");
        }
    }


    private boolean buildOrder(HttpServletRequest request, Order order, Map<String, String> redirect) {

        String status = request.getParameter("status");
        String paymentType = request.getParameter("payment_type");
        String price = request.getParameter("price");
        String name = request.getParameter("name");
        String time = request.getParameter("time");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String room = request.getParameter("room");
        String porch = request.getParameter("porch");
        String floor = request.getParameter("floor");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String comments = request.getParameter("comments");

        return orderParser.parseForClientWithBase(redirect, order, street, comments, floor, porch, room, house, name, tel, email, time, status, paymentType, price);
    }

    private void update(HttpServletRequest request, HttpServletResponse response, String referer, Map<String, String> redirect, Order order) throws IOException {
        if (buildOrder(request, order, redirect)) {
            try {
                if (orderService.update(order)) {
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "/order-list?pagination=1");
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

}
