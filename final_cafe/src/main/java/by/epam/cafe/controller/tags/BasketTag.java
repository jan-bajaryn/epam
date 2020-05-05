package by.epam.cafe.controller.tags;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.entity.impl.Product;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class BasketTag extends TagSupport {
    private static final Logger log = LogManager.getLogger(BasketTag.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public int doStartTag() throws JspException {
        try {

            Map<Product, Integer> basket = takeBasket();

            Integer c = sizeBasket(basket);
            log.debug("c = {}", c);
            pageContext.getOut().write(c.toString());
        } catch (IOException | ServiceException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }


    private Integer sizeBasket(Map<Product, Integer> basket) {
        return basket.values().stream()
                .reduce(0, Integer::sum);
    }


    private Map<Product, Integer> takeBasket() throws ServiceException {

        HttpServletRequest req = ((HttpServletRequest) pageContext.getRequest());
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