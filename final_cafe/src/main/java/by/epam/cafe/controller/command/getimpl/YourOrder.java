package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Forward;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.service.db.OrderService;
import by.epam.cafe.service.exception.IllegalPathParamException;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.helper.PathVarCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class YourOrder extends by.epam.cafe.controller.command.Command {


    private static final Logger log = LogManager.getLogger(YourOrder.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final OrderService orderService = serviceFactory.getOrderService();


    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            Order order = orderService.findEntityById(id);
            if (order != null) {

                log.info("productMap = {}", order.getProducts());
                log.info("order.getProducts() = {}", order.getProducts());

                Integer sum = order.getProducts().entrySet().stream()
                        .map(p -> p.getKey().getPrice() * p.getValue())
                        .reduce(0, Integer::sum);

                request.setAttribute("order", order);
                request.setAttribute("sum", sum);

                log.debug("order.getProducts() = {}", order.getProducts());
                request.setAttribute("productMap", order.getProducts());
                return new Forward("/your-order.jsp");
            }
        } catch (ServiceException e) {
            log.debug("e:", e);
        }
        return new SendError(500);
    }
}
