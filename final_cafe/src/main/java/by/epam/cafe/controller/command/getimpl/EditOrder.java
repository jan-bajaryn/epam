package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Forward;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;

public class EditOrder extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(EditOrder.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final OrderService orderService = serviceFactory.getOrderService();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            log.info("execute: id = {}", id);
            Order order = orderService.findEntityById(id);
            if (order != null) {
                request.setAttribute("order", order);
                request.setAttribute("statuses", EnumSet.complementOf(EnumSet.of(OrderStatus.WAITING)));
                request.setAttribute("types", PaymentType.values());
                request.setAttribute("time", parseToTime(order.getDeliveryInf().getDeliveryTime()));
                return new Forward("/edit-order.jsp");
            } else {
                log.info("order is null");
                return new SendError(500);
            }
        } catch (ServiceException e) {
            log.info("Problem in parsing");
            return new Forward("/edit-order.jsp");
        }

    }

    private String parseToTime(LocalDateTime deliveryTime) {
        log.debug("deliveryTime = {}", deliveryTime);
        String timeSt = deliveryTime.format(formatter);
        log.debug("timeSt = {}", timeSt);
        return timeSt;
    }
}
