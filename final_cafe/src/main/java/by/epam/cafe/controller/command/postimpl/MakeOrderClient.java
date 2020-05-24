package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.User;
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

public class MakeOrderClient extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(MakeOrderClient.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final OrderParser orderParser = serviceFactory.getOrderParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String referer = request.getHeader("referer");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<String, String> redirect = new HashMap<>();

        try {
            Order order = orderService.findCurrentByUserId(user.getId());
            if (order != null) {
                return update(request, response, referer, redirect, order);
            }
        } catch (ServiceException e) {
            log.debug("e: ", e);
        }
        redirect.put("no_products_error", "true");
        session.setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

    private ResponseObject update(HttpServletRequest request, HttpServletResponse response, String referer, Map<String, String> redirect, Order order) throws IOException {
        if (buildOrder(request, order, redirect)) {
            try {
                if (orderService.update(order)) {
                    return new Redirect("/your-order/" + order.getId());
                }
            } catch (ServiceException e) {
                log.debug("e: ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
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
