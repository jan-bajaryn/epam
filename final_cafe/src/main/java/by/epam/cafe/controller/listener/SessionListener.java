package by.epam.cafe.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

//@WebListener
public class SessionListener implements HttpSessionAttributeListener {

    private static final Logger log = LogManager.getLogger(SessionListener.class);


    public void attributeRemoved(HttpSessionBindingEvent ev) {
        log.info("attributeRemoved: value = {}, name = {}, id = {}", ev.getValue(), ev.getName(), ev.getSession().getId());
    }

    public void attributeAdded(HttpSessionBindingEvent ev) {
        log.info("attributeAdded: value = {}, name = {}, id = {}", ev.getValue(), ev.getName(), ev.getSession().getId());
    }

    public void attributeReplaced(HttpSessionBindingEvent ev) {
        log.debug("attributeReplaced: value = {}, name = {}, id = {}", ev.getValue(), ev.getName(), ev.getSession().getId());

    }
}
