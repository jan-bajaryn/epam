package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.exception.IllegalPathParamException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.PathVarCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditOrderCommand extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(EditOrderCommand.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            log.info("execute: id = {}", id);
            Order order = orderService.findEntityById(id);
            if (order != null) {
                request.setAttribute("order", order);
                request.getRequestDispatcher("/WEB-INF/jsp/edit-order.jsp").forward(request, response);
            } else {
                log.info("order is null");
                request.getRequestDispatcher("/WEB-INF/jsp/errors/something_went_wrong.jsp").forward(request, response);
            }
        } catch (IllegalPathParamException e) {
            log.info("Problem in parsing");
            request.getRequestDispatcher("/WEB-INF/jsp/edit-order.jsp").forward(request, response);
        }

    }
}
