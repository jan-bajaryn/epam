package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.parser.full.UserParser;
import by.epam.cafe.service.parser.helper.NullIfEmptyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class RegistrationRealization extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(RegistrationRealization.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();

    private final NullIfEmptyService nullEmpt = serviceFactory.getNullIfEmptyService();
    private final UserParser userParser = serviceFactory.getUserParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("execute: begin");

        Map<String, String> redirect = new HashMap<>();
        User build = validateAndTakeParams(request, redirect);
        log.debug("execute: build = {}", build);
        if (build != null) {
            try {
                if (userService.create(build) != null) {
                    return new Redirect("/login");
                } else {
                    return new SendError(403);
                }
            } catch (ServiceException e) {
                log.debug("e: ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        return new SendError(403);
    }


    private User validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect) {
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
        String token = request.getParameter("token");

        return userParser.parseRegistrationUserWithToken(redirect, email, phone, username, password, name, surname, street, house, room, porch, floor, token);
    }

}
