package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
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

public class CreateUser extends by.epam.cafe.controller.command.Command {


    private static final Logger log = LogManager.getLogger(CreateUser.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();

    private final UserParser userParser = serviceFactory.getUserParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String referer = request.getHeader("referer");

        Map<String, String> redirect = new HashMap<>();

        User build = validateAndTakeParams(request, redirect);

        if (build != null) {

            try {
                if (userService.create(build) != null) {
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

    private User validateAndTakeParams(HttpServletRequest req, Map<String, String> redirect) {

        String usernameParam = req.getParameter("username");
        String passwordParam = req.getParameter("password");
        String roleParam = req.getParameter("role");
        String nameParam = req.getParameter("name");
        String surnameParam = req.getParameter("surname");
        String houseParam = req.getParameter("house");
        String roomParam = req.getParameter("room");
        String porchParam = req.getParameter("porch");
        String floorParam = req.getParameter("floor");
        String phoneParam = req.getParameter("phone");
        String emailParam = req.getParameter("email");
        String streetParam = req.getParameter("street");

        return userParser.parseUser(redirect, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam, roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam);
    }

}
