package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.encryption.ApplicationEncrypt;
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
import java.util.HashMap;
import java.util.Map;

import static by.epam.cafe.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class Login extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(Login.class);


    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private final ApplicationEncrypt applicationEncrypt = serviceFactory.getApplicationEncrypt();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String targetUrl = request.getParameter("target_url");

        HttpSession session = request.getSession();
        Map<String, String> redirect = new HashMap<>();

        try {
            User user = userService.findUserByUsername(username);

            if (user != null && user.getPassword().equals(applicationEncrypt.calcUserPasswordHash(password))) {
                putUser(request, user);
                session.removeAttribute("basket");
                return redirect(request, targetUrl);
            }
        } catch (ServiceException e) {
            log.debug("e: ", e);
        }
        redirect.put("authentication_error", "true");
        redirect.put("target_url", targetUrl);
        session.setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect("/login");
    }

    private ResponseObject redirect(HttpServletRequest request, String targetUrl) {
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
