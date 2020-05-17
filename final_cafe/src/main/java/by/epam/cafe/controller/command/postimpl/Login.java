package by.epam.cafe.controller.command.postimpl;

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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!loginValidator.isValid(username, password)) {
            response.sendRedirect(request.getContextPath() +
                    request.getServletPath() + "/something_went_wrong");
        }


        try {
            User user = userService.findUserByUsername(username);

            if (user != null && user.getPassword().equals(password)) {
                putUser(request, user);
                redirect(request, response);
            } else {
                response.sendRedirect(request.getContextPath() +
                        request.getServletPath() + "/something_went_wrong");
            }
        } catch (ServiceException e) {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/something_went_wrong");
        }

    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String targetUrl = request.getParameter("target_url");
        log.debug("targetUrl = {}", targetUrl);
        if (targetUrl != null && !targetUrl.isEmpty() && targetUrl.startsWith(request.getContextPath())) {
            response.sendRedirect(targetUrl);
        } else {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + "/");
        }
    }

    private void putUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }
}
