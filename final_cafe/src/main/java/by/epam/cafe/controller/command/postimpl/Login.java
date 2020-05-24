package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;
import by.epam.cafe.service.validator.parts.LoginValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(Login.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final LoginValidator loginValidator = serviceFactory.getLoginValidator();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!loginValidator.isValid(username, password)) {
            return new SendError(500);
        }

        try {
            User user = userService.findUserByUsername(username);

            if (user != null && user.getPassword().equals(password)) {
                putUser(request, user);
                return redirect(request, response);
            }
        } catch (ServiceException e) {
            log.debug("e: ", e);
        }
        return new SendError(500);

    }

    private ResponseObject redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String targetUrl = request.getParameter("target_url");
        log.debug("targetUrl = {}", targetUrl);
        if (targetUrl != null && !targetUrl.isEmpty() && targetUrl.startsWith(request.getContextPath())) {
            return new Redirect(targetUrl, false);
        }
        return new Redirect("/");
    }

    private void putUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }
}
