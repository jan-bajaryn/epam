package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.UserService;
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

public class EditUser extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(EditUser.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            log.info("execute: id = {}", id);
            User user = userService.findEntityById(id);
            log.info("execute: user = {}", user);
            if (user != null) {
                request.setAttribute("user", user);
                request.setAttribute("roles", Role.values());
                request.getRequestDispatcher("/WEB-INF/jsp/admin/edit-user.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/jsp/errors/something_went_wrong.jsp").forward(request, response);
            }
        } catch (ServiceException e) {
            request.getRequestDispatcher("/WEB-INF/jsp/errors/something_went_wrong.jsp").forward(request, response);
        }
    }
}
