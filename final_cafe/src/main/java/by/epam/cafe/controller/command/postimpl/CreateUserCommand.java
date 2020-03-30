package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.UserService;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreateUserCommand extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(CreateUserCommand.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();
    private final UserValidator userValidator = serviceFactory.getUserValidator();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
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

        try {
            User build = User.newBuilder()
                    .username(username)
                    .password(password)
                    .role(Role.valueOf(role))
                    .name(name)
                    .surname(surname)
                    .house(house)
                    .room(room)
                    .porch(Integer.valueOf(porch))
                    .floor(Integer.valueOf(floor))
                    .phone(phone)
                    .email(email)
                    .creation(LocalDateTime.now())
                    .isBlocked(false)
                    .street(street)
                    .build();

            if (userValidator.validWithoutId(build) && userService.create(build)) {
                response.sendRedirect(request.getServletPath() + "/admin/user-list");
            } else {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
            }


        } catch (IllegalArgumentException e) {
            log.error("e:", e);
            String path = request.getContextPath() + request.getServletPath();
            log.info("execute: path = {}", path);
            response.sendRedirect(path + "/something_went_wrong");
        }


    }
}
