package by.epam.cafe.controller.filter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RedirectFilter implements Filter {

    private static final Logger log = LogManager.getLogger(RedirectFilter.class);
    public static final String PREFIX = "redirect_";
    public static final String REDIRECTED_INFO = "redirected_info";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.debug("begin filter");
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            this.doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
        } else {
            throw new ServletException("non-HTTP request or response");
        }
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("begin method");
        HttpSession session = request.getSession();
        Map<String, String> info = calcRedirectedInfo(session);

        for (Map.Entry<String, String> entry : info.entrySet()) {
            request.setAttribute(PREFIX + entry.getKey(), entry.getValue());
        }
        session.removeAttribute(REDIRECTED_INFO);

//        VendorMap map = new VendorMap();
//        request.setAttribute("rw", map);

        log.debug("now will be doFilter");
        chain.doFilter(request, response);
    }

    private Map<String, String> calcRedirectedInfo(HttpSession session) {
        Map<String, String> info = (Map<String, String>) session.getAttribute(REDIRECTED_INFO);
        return info == null ? new HashMap<>() : info;
    }

    @Override
    public void destroy() {
    }
}
