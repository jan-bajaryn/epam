package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.NullIfEmptyService;
import by.epam.cafe.service.parser.full.UserParser;
import by.epam.cafe.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class EditAdmin extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(EditAdmin.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();
    private final UserValidator userValidator = serviceFactory.getUserValidator();


    private final UserParser userParser = serviceFactory.getUserParser();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Begin  EditAdminCommand");


        String referer = request.getHeader("referer");

        Map<String, String> redirect = new HashMap<>();
        User user = validateAndTakeParams(request, redirect);

        if (user != null) {

            try {
                if (userService.update(user)) {
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/user-list?pagination=1");
                } else {
                    request.setAttribute("unknown_error", "true");
                    response.sendRedirect(referer);
                }
            } catch (ServiceException e) {
                request.setAttribute("unknown_error", "true");
                response.sendRedirect(referer);
            }
        } else {
            response.sendRedirect(referer);
            request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        }

    }


    private User validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect) {

        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String house = request.getParameter("house");
        String room = request.getParameter("room");
        String porch = request.getParameter("porch");
        String floor = request.getParameter("floor");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String isBlocked = request.getParameter("isBlocked");
        log.info("isBlocked = {}", isBlocked);

        return userParser.parseUserWithId(redirect, id, username, password, role, name, surname, house, room, porch, floor, phone, email, street, isBlocked);
    }

}
