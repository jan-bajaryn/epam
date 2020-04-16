package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.UserService;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.NullIfEmptyService;
import by.epam.cafe.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class RegistrationCommand extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(RegistrationCommand.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();
    private final UserValidator userValidator = serviceFactory.getUserValidator();

    private final NullIfEmptyService nullEmpt = serviceFactory.getNullIfEmptyService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        log.info("execute: begin");

        try {
            User build = buildUser(request);
            boolean validate = userValidator.validWithoutId(build);
            log.debug("execute: validate = {}", validate);
            if (validate && userService.create(build)) {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/login");
            } else {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
            }


        } catch (IllegalArgumentException | NullPointerException e) {
            log.error("e:", e);
            String path = request.getContextPath() + request.getServletPath();
            log.info("execute: path = {}", path);
            response.sendRedirect(path + "/something_went_wrong");
        }


    }

    private User buildUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String room = request.getParameter("room");
        String porch = request.getParameter("porch");
        String floor = request.getParameter("floor");

        return User.newBuilder()
                .username(username)
                .password(password)
                .role(Role.CLIENT)
                .name(name)
                .surname(surname)
                .house(nullEmpt.nullIfEmptyString(house))
                .room(nullEmpt.nullIfEmptyString(room))
                .porch(nullEmpt.nullIfEmptyInteger(porch))
                .floor(nullEmpt.nullIfEmptyInteger(floor))
                .phone(nullEmpt.nullIfEmptyString(phone))
                .email(email)
                .creation(LocalDateTime.now())
                .isBlocked(false)
                .street(nullEmpt.nullIfEmptyString(street))
                .build();
    }

}