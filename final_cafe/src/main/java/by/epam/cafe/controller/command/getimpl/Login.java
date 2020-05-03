package by.epam.cafe.controller.command.getimpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends by.epam.cafe.controller.command.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String tarUrl = (String) session.getAttribute("target_url");
        if (tarUrl != null && !tarUrl.isEmpty()) {
            request.setAttribute("target_url", tarUrl);

            // remove to make sure the url will be put only 1 time to
            // the login form if user will want to change his mind

            session.removeAttribute("target_url");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
