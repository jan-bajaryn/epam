package by.epam.cafe.controller.filter;

import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.db.UserService;
import by.epam.cafe.service.exception.ServiceException;
import by.epam.cafe.service.factory.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DenyBlockedUser implements Filter {

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.getUserService();

    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && user.isBlocked()) {
            session.setAttribute("user", null);
            res.sendError(403);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            this.doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
        } else {
            throw new ServletException("non-HTTP request or response");
        }
    }

    @Override
    public void destroy() {

    }
}
