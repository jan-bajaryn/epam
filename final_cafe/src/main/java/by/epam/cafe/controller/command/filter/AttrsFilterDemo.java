package by.epam.cafe.controller.command.filter;


import by.epam.cafe.controller.tags.VendorMap;

import javax.servlet.*;
import java.io.IOException;

public class AttrsFilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        request.setCharacterEncoding("UTF-8");
//        chain.doFilter(request, response);
//        request.setAttribute("a","b");
        VendorMap map = new VendorMap();
        request.setAttribute("rw", map);
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {}
}
