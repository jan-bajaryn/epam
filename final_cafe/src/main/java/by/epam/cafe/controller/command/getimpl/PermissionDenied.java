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


public class PermissionDenied extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(PermissionDenied.class);


    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        Role role = ((Role) request.getAttribute("role"));
        if (role == Role.ANON) {
            String targetUrl = request.getRequestURI() + "?" + request.getQueryString();

            log.debug("targetUrl = {}", targetUrl);
            HttpSession session = request.getSession();
            session.setAttribute("target_url", targetUrl);
            return new Redirect(CommandGetFactory.LOGIN_PAGE);
        } else {
            return new SendError(500);
        }
    }
}
