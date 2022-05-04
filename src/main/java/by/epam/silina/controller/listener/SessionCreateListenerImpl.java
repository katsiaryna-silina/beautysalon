package by.epam.silina.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        /* Session is created. */
        log.info("--------> sessionCreated : {}", httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        /* Session is destroyed. */
        log.info("--------> sessionDestroyed : {}", httpSessionEvent.getSession().getId());
    }
}
