package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.impl.Order;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.PaginationCalculator;
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

    private final PaginationCalculator paginationCalculator = serviceFactory.getPaginationCalculator();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int part = paginationCalculator.calculatePartParam(request.getParameter("pagination"));

            List<Order> all = orderService.findAllByPart((part - 1) * 10, 10);
            log.info("execute: all = {}", all);
            request.setAttribute("orders", all);
            request.getRequestDispatcher("/WEB-INF/jsp/order-list.jsp").forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }
    }
}
