package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Forward;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.pagination.PaginationService;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.pagination.PaginationCalculator;
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
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int part = paginationCalculator.calculatePartParam(request.getParameter("pagination"));
            List<User> all = userService.findAllByPart(part);
            log.info("execute: all = {}", all);
            request.setAttribute("users", all);
            request.setAttribute("paginationMap", paginationService.calculate(userService.count(), part, MAX_PAGINATION_ELEMENTS));
            return new Forward("/admin/user-list.jsp");
        } catch (ServiceException e) {
            return new SendError(500);
        }
    }
}
