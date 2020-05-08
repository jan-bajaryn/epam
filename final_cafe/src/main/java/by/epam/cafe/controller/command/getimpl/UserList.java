package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.PaginationService;
import by.epam.cafe.service.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.PaginationCalculator;
import by.epam.cafe.service.parser.impl.PaginationCalculatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.cafe.config.Configuration.MAX_PAGINATION_ELEMENTS;

public class UserList extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(UserList.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();

    private final PaginationCalculator paginationCalculator = serviceFactory.getPaginationCalculator();

    private final PaginationService paginationService = serviceFactory.getPaginationService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int part = paginationCalculator.calculatePartParam(request.getParameter("pagination"));
            List<User> all = userService.findAllByPart((part - 1) * MAX_PAGINATION_ELEMENTS, MAX_PAGINATION_ELEMENTS);
            log.info("execute: all = {}", all);
            request.setAttribute("users", all);
            request.setAttribute("paginationMap", paginationService.calculate(userService.findAll().size(), part, MAX_PAGINATION_ELEMENTS));
            request.getRequestDispatcher("/WEB-INF/jsp/admin/user-list.jsp").forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }
    }
}
