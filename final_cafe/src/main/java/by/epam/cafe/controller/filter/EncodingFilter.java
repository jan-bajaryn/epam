package by.epam.cafe.controller.filter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger log = LogManager.getLogger(EncodingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        log.debug("request.getCharacterEncoding() = {}", request.getCharacterEncoding());
//        log.debug("response.getCharacterEncoding() = {}", response.getCharacterEncoding());
        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        log.debug("request.getCharacterEncoding() = {}", request.getCharacterEncoding());
//        log.debug("response.getCharacterEncoding() = {}", response.getCharacterEncoding());
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
