package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderList extends by.epam.cafe.controller.command.Command {
    private static final Logger log = LogManager.getLogger(OrderList.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> all = orderService.findAll();
        log.info("execute: all = {}", all);
        request.setAttribute("orders", all);
        request.getRequestDispatcher("/WEB-INF/jsp/order-list.jsp").forward(request, response);
    }
}
