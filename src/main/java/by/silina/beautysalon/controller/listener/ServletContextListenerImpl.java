package by.silina.beautysalon.controller.listener;

import by.silina.beautysalon.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        ConnectionPool.getInstance();

        var serverInfo = servletContextEvent.getServletContext().getServerInfo();
        var contextPath = servletContextEvent.getServletContext().getContextPath();
        log.debug("Servlet context was initialized. Server info: {}. Context path: {}.", serverInfo, contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        ConnectionPool.getInstance().destroyPool();

        var contextPath = servletContextEvent.getServletContext().getContextPath();
        log.debug("Servlet context was destroyed. Context path: {}", contextPath);
    }
}
