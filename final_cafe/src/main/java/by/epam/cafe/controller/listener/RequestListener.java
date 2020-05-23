package by.epam.cafe.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

//@WebListener
public class RequestListener implements ServletRequestListener {

    private static final Logger log = LogManager.getLogger(RequestListener.class);


    public void requestInitialized(ServletRequestEvent ev) {
        HttpServletRequest req = (HttpServletRequest) ev.getServletRequest();
        log.debug("requestInitialized: uri = {}, req.getRequestedSessionId() = {}", req.getRequestURI(), req.getRequestedSessionId());
    }

    public void requestDestroyed(ServletRequestEvent ev) {
        HttpServletRequest req = (HttpServletRequest) ev.getServletRequest();
        log.debug("requestDestroyed: uri = {}, req.getRequestedSessionId() = {}", req.getRequestURI(), req.getRequestedSessionId());
    }
}