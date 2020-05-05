package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.dto.UserDTO;
import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DisplayOrder extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(DisplayOrder.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            Map<Product, Integer> basket = takeBasket(request);
            request.setAttribute("productMap", basket);

            request.setAttribute("sum", calcSum(basket));
            sendUserDTO(request);

            request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }
    }

    private void sendUserDTO(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("info", new UserDTO(user));
        }
    }

    private Integer calcSum(Map<Product, Integer> basket) {
        return basket.entrySet().stream()
                .map(e -> e.getKey().getPrice() * e.getValue())
                .reduce(0, Integer::sum);
    }


    private Map<Product, Integer> takeBasket(HttpServletRequest req) throws ServiceException {

        HttpSession session = req.getSession();

        log.debug("session took");
        Role role = (Role) req.getAttribute("role");
        log.debug("role = {}", role);

        Map<Product, Integer> basket;

        if (role == Role.ANON) {
            return returnAnonBasket(session);
        } else if (role == Role.CLIENT) {
            return returnClientBasket(session);
        } else {
            return new HashMap<>();
        }
    }

    private Map<Product, Integer> returnAnonBasket(HttpSession session) {
        Map<Product, Integer> basket;
        Object basketObj = session.getAttribute("basket");
        if (basketObj == null) {
            basket = new HashMap<>();
        } else {
            basket = ((Map<Product, Integer>) basketObj);
        }
        return basket;
    }

    private Map<Product, Integer> returnClientBasket(HttpSession session) throws ServiceException {
        User user = (User) session.getAttribute("user");
        Order order = orderService.findCurrentByUserId(user.getId());
        if (order == null) {
            return new HashMap<>();
        }
        return order.getProducts();
    }
}
