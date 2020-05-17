package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.OrderService;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PutItemClient extends by.epam.cafe.controller.command.Command {
    private static final Logger log = LogManager.getLogger(PutItemClient.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        log.debug("begin method");
        try {
            String productIdSt = request.getParameter("variant");

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            Order currentByUserId = orderService.findOrCreateCurrentByUserId(user.getId());

            log.debug("productIdSt = {}", productIdSt);

            Integer orderId = currentByUserId.getId();
            Integer prodId = Integer.valueOf(productIdSt);

            orderService.plusProduct(orderId, prodId);

            response.sendRedirect(request.getHeader("referer"));
        } catch (NumberFormatException | ServiceException e) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }
    }
}
