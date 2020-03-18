package by.epam.task13.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class ChangeLanguageCommand {
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        Locale l = (Locale) Config.get(session, Config.FMT_LOCALE);

        if (l == null) {
            Config.set(session, Config.FMT_LOCALE, new Locale("en", "US"));
        } else if (l.getCountry().equalsIgnoreCase("ru")) {
            Config.set(session, Config.FMT_LOCALE, new Locale("en", "US"));
        } else {
            Config.set(session, Config.FMT_LOCALE, new Locale("ru", "RU"));
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
