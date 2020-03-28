package by.epam.cafe.controller.filter;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RolesFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("role", Role.ANON);
        } else {
            session.setAttribute("role", user.getRole());
        }
        chain.doFilter(req, res);
    }
}
