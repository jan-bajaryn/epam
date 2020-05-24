package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.service.db.OrderService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlusProductOperator extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(PlusProductOperator.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    OrderService orderService = serviceFactory.getOrderService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        log.debug("begin method");
        try {
            String orderIdSt = request.getParameter("order_id");
            String productIdSt = request.getParameter("product_id");

            log.debug("orderIdSt = {}", orderIdSt);
            log.debug("productIdSt = {}", productIdSt);

            Integer orderId = Integer.valueOf(orderIdSt);
            Integer prodId = Integer.valueOf(productIdSt);

            orderService.plusProduct(orderId, prodId);
            return new Redirect("/add-products/" + orderIdSt + "?pagination=1");
        } catch (NumberFormatException | ServiceException e) {
            return new SendError(500);
        }
    }
}
