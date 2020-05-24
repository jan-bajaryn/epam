package by.epam.cafe.controller.utils.impl;

import by.epam.cafe.controller.utils.ResponseObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirect implements ResponseObject {
    private String url;
    private boolean withPrefix = true;

    public Redirect(String url) {
        this.url = url;
    }

    public Redirect(String url, boolean withPrefix) {
        this.url = url;
        this.withPrefix = withPrefix;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (withPrefix) {
            res.sendRedirect(req.getContextPath() + req.getServletPath() + url);
        } else {
            res.sendRedirect(url);
        }
    }
}
