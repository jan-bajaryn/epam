package by.epam.cafe.controller.command.postimpl;

import by.epam.cafe.controller.command.Languages;
import by.epam.cafe.controller.command.PermissionDeniedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class ChangeLanguage extends by.epam.cafe.controller.command.Command {

    private static final Logger log = LogManager.getLogger(ChangeLanguage.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PermissionDeniedException {
        String lang = request.getParameter("lang");
        Optional<Languages> any = Arrays.stream(Languages.values())
                .filter(l -> l.getKey().equals(lang))
                .findAny();

        if (any.isPresent()) {
            //logic
            HttpSession session = request.getSession();
            Config.set(session, Config.FMT_LOCALE, any.get().getLocale());
        } else {
            // TODO realize locerr if need
            request.setAttribute("locerr", true);
        }

        String referer = request.getHeader("referer");
        log.info("referer = {}", referer);
        response.sendRedirect(referer);

    }
}
