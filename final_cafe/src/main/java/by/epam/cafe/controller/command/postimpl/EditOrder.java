package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.service.db.OrderService;
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
import java.util.*;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class EditOrder extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(EditOrder.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final OrderParser orderParser = serviceFactory.getOrderParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String referer = request.getHeader("referer");

        HttpSession session = request.getSession();
        Map<String, String> redirect = new HashMap<>();

        try {
            String id = request.getParameter("id");
            Order order = orderService.findEntityById(Integer.valueOf(id));
            if (order != null) {
                return update(request, response, referer, redirect, order);
            }
        } catch (ServiceException e) {
            log.debug("e: ", e);
        }
        redirect.put("fatal_id", "true");
        session.setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect("/order-list?pagination=1");
    }


    private boolean buildOrder(HttpServletRequest request, Order order, Map<String, String> redirect) {

        String status = request.getParameter("status");
        String paymentType = request.getParameter("payment_type");
        String price = request.getParameter("price");
        String name = request.getParameter("name");
        String time = request.getParameter("time");
        log.debug("buildOrder: time = {}", time);
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String room = request.getParameter("room");
        String porch = request.getParameter("porch");
        String floor = request.getParameter("floor");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String comments = request.getParameter("comments");

        return orderParser.parseForOperatorWithBase(redirect, order, street, comments, floor, porch, room, house, name, tel, email, time, status, paymentType, price);
    }

    private ResponseObject update(HttpServletRequest request, HttpServletResponse response, String referer, Map<String, String> redirect, Order order) throws IOException {
        if (buildOrder(request, order, redirect)) {
            try {
                if (orderService.update(order)) {
                    return new Redirect("/order-list?pagination=1");
                }
            } catch (ServiceException e) {
                log.debug("e: ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

}
