package by.silina.beautysalon.controller.listener;

import by.silina.beautysalon.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * The ServletContextListenerImpl class with methods related to servlet context.
 *
 * @author Silina Katsiaryna
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Is called when the servlet context is initialized(when the Web application is deployed).
     *
     * @param event ServletContextEvent. The servlet context event.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ConnectionPool.getInstance();

        var serverInfo = event.getServletContext().getServerInfo();
        var contextPath = event.getServletContext().getContextPath();
        log.debug("Servlet context was initialized. Server info: {}. Context path: {}.", serverInfo, contextPath);
    }

    /**
     * Is called when the servlet Context is undeployed or Application Server shuts down.
     *
     * @param event ServletContextEvent. The servlet context event.
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ConnectionPool.getInstance().destroyPool();

        var contextPath = event.getServletContext().getContextPath();
        log.debug("Servlet context was destroyed. Context path: {}", contextPath);
    }
}
