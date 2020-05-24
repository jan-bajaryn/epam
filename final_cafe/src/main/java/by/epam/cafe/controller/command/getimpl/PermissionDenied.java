package by.epam.cafe.controller.command.getimpl;

import by.epam.cafe.controller.command.PermissionDeniedException;
import by.epam.cafe.controller.factory.impl.CommandGetFactory;
import by.epam.cafe.controller.utils.ResponseObject;
import by.epam.cafe.controller.utils.impl.Redirect;
import by.epam.cafe.controller.utils.impl.SendError;
import by.epam.cafe.entity.enums.Role;
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


public class PermissionDenied extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(PermissionDenied.class);


    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        Role role = ((Role) request.getAttribute("role"));
        HttpSession session = request.getSession();
        Map<String ,String > redirect = new HashMap<>();

        if (role == Role.ANON) {
            String targetUrl = request.getRequestURI() + "?" + request.getQueryString();

            log.debug("targetUrl = {}", targetUrl);
            redirect.put("target_url",targetUrl);
            session.setAttribute(REDIRECTED_INFO, redirect);

            return new Redirect(CommandGetFactory.LOGIN_PAGE);
        } else {
            return new SendError(403);
        }
    }
}
