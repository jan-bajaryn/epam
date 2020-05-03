package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MinusItemClient extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(MinusItemClient.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        log.debug("begin method");
        try {
            String productIdSt = request.getParameter("variant");

            log.debug("productIdSt = {}", productIdSt);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            Order currentByUserId = orderService.findOrCreateCurrentByUserId(user.getId());

            Integer orderId = currentByUserId.getId();

            Integer prodId = Integer.valueOf(productIdSt);

            orderService.minusOrDelete(orderId, prodId);

            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/order");
        } catch (NumberFormatException | ServiceException e) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }
    }
}
