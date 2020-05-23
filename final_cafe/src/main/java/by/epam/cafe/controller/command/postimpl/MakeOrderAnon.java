package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.Product;
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

public class MakeOrderAnon extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(MakeOrderAnon.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final OrderParser orderParser = serviceFactory.getOrderParserImpl();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String referer = request.getHeader("referer");

        Map<String, String> redirect = new HashMap<>();
        HttpSession session = request.getSession();
        Order build = buildOrder(request, session, redirect);


        log.debug("first0");
        if (build != null) {
            log.debug("first1");
            try {
                if (orderService.create(build) != null) {
                    log.debug("first2");
                    session.setAttribute("basket", null);
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "/your-order/" + build.getId());
                } else {
                    request.setAttribute("unknown_error", "true");
                    response.sendRedirect(referer);
                }
            } catch (ServiceException e) {
                request.setAttribute("unknown_error", "true");
                response.sendRedirect(referer);
            }
        } else {
            session.setAttribute(REDIRECTED_INFO, redirect);
            response.sendRedirect(referer);
        }
    }

    private Order buildOrder(HttpServletRequest request, HttpSession session, Map<String, String> redirect) {
        Map<Product, Integer> basket = takeBasket(session);

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

        return orderParser.parse(redirect, street, comments, floor, porch, room, house, name, tel, email, time, basket);

    }


    private Map<Product, Integer> takeBasket(HttpSession session) {
        Map<Product, Integer> basket;
        Object basketObj = session.getAttribute("basket");
        if (basketObj == null) {
            basket = new HashMap<>();
        } else {
            basket = ((Map<Product, Integer>) basketObj);
        }
        return basket;
    }

}
