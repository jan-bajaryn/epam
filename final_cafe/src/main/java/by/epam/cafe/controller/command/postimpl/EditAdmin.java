package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.full.UserParser;
import by.epam.cafe.service.parser.parts.impl.IdParser;
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

    private final IdParser idParser = serviceFactory.getIdParser();


    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Begin  EditAdminCommand");

        String referer = request.getHeader("referer");
        Map<String, String> redirect = new HashMap<>();

        try {
            User user = findUser(request);

            if (validateAndTakeParams(request, redirect, user)) {
                if (userService.update(user)) {
                    return new Redirect("/admin/user-list?pagination=1");
                }
                redirect.put("unknown_error", "true");
            }
        } catch (ServiceException e) {
            log.debug("e: ", e);
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }


    private boolean validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect, User user) {
        String username = request.getParameter("username");
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

        return userParser.parseUserWithId(redirect, user, username, role, name, surname, house, room, porch, floor, phone, email, street, isBlocked);
    }

    private User findUser(HttpServletRequest request) throws ServiceException {
        String idParam = request.getParameter("id");
        OptionalNullable<Integer> parse = idParser.parse(idParam);
        if (parse.isPresent()) {
            return userService.findEntityById(parse.get());
        }
        return null;
    }

}
