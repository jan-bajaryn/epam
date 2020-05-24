package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.OrderService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteAllProdFromBasketClient extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(DeleteAllProdFromBasketClient.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    OrderService orderService = serviceFactory.getOrderService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        log.debug("begin method");
        try {
            String productIdSt = request.getParameter("id");

            log.debug("productIdSt = {}", productIdSt);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Order currentByUserId = orderService.findOrCreateCurrentByUserId(user.getId());
            Integer orderId = currentByUserId.getId();
            Integer prodId = Integer.valueOf(productIdSt);

            orderService.deleteProduct(orderId, prodId);

            return new Redirect("/order");
        } catch (NumberFormatException | ServiceException e) {
            return new SendError(500);
        }
    }
}
