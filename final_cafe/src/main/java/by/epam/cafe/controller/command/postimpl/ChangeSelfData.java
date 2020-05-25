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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class ChangeSelfData extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(ChangeSelfData.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final UserService userService = serviceFactory.getUserService();

    private final UserParser userParser = serviceFactory.getUserParser();


    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {

        Map<String, String> redirect = new HashMap<>();
        HttpSession session = request.getSession();
        User user = ((User) session.getAttribute("user"));

        boolean b = validateAndTakeParams(request, redirect, user);

        if (b) {
            try {
                if (userService.update(user)) {
                    return new Redirect("/cabinet");
                }
            } catch (ServiceException e) {
                log.debug("e: ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect("/cabinet");

    }


    private boolean validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect, User base) {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String house = request.getParameter("house");
        String room = request.getParameter("room");
        String porch = request.getParameter("porch");
        String floor = request.getParameter("floor");
        String phone = request.getParameter("phone");
        String street = request.getParameter("street");

        boolean parse = userParser.parseWithBaseSelfChange(redirect, base, name, surname, house, room, porch, floor, phone, street);

        String passwordOld = request.getParameter("old_password");
        String passwordNew = request.getParameter("new_password");


        if (passwordOld != null && passwordNew != null) {
            parse = userParser.parseChangePassword(redirect, base, passwordOld, passwordNew) && parse;
        }

        return parse;
    }

}
