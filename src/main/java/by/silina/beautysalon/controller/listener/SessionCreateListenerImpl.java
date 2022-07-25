package by.silina.beautysalon.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;
import static by.silina.beautysalon.controller.command.PagePath.INDEX;

/**
 * The SessionCreateListenerImpl class with methods related to session.
 *
 * @author Silina Katsiaryna
 */
@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Is called when the session is created.
     *
     * @param event ServletContextEvent. The servlet context event.
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        var session = event.getSession();
        session.setAttribute(CURRENT_PAGE, INDEX);
        session.setAttribute(LOCALE, LOCALE_EN_US);
        log.info("Session with id={} was created.", session.getId());
    }

    /**
     * Is called when the session is destroyed.
     *
     * @param event ServletContextEvent. The servlet context event.
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        log.info("Session with id={} was destroyed.", event.getSession().getId());
    }
}
