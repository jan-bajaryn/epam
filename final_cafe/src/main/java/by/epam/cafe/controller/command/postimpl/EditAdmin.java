package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.full.UserParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class EditAdmin extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(EditAdmin.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();

    private final UserParser userParser = serviceFactory.getUserParser();


    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Begin  EditAdminCommand");

        String referer = request.getHeader("referer");

        Map<String, String> redirect = new HashMap<>();
        User user = validateAndTakeParams(request, redirect);

        if (user != null) {
            try {
                if (userService.update(user)) {
                    return new Redirect("/admin/user-list?pagination=1");
                }
            } catch (ServiceException e) {
                log.debug("e: ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
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
