package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.service.OrderService;
import by.epam.cafe.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelOrder extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(CancelOrder.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String id = request.getParameter("id");

        try {
            Integer idInt = Integer.valueOf(id);

            boolean update = orderService.cancelOrDeleteById(idInt);
            log.debug("execute: update = {}", update);
            response.sendRedirect(request.getContextPath()+request.getServletPath()+"/order-list");
        } catch (NumberFormatException e) {
            log.debug("e: ",e);
            response.sendRedirect(request.getContextPath()+request.getServletPath()+"/something_went_wrong");
        }
    }
}
