package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.UserService;
import by.epam.cafe.service.exception.ServiceException;
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

public class EditAdmin extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(EditAdmin.class);
    private static final String IS_BLOCKED = "1";


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();
    private final UserValidator userValidator = serviceFactory.getUserValidator();

    private final NullIfEmptyService nullEmpt = serviceFactory.getNullIfEmptyService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Begin  EditAdminCommand");

        try {
            User user = buildUser(request);

            try {
                if (userValidator.isValid(user) && userService.update(user)) {
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "/admin/user-list");
                } else {
                    response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
                }
            } catch (ServiceException e) {
                response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            log.debug("e: ", e);
        }

    }

    private User buildUser(HttpServletRequest request) {
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

        return User.newBuilder()
                .id(Integer.valueOf(id))
                .username(username)
                .password(password)
                .role(Role.valueOf(role))
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
                .isBlocked(IS_BLOCKED.equals(isBlocked))
                .build();
    }

}
