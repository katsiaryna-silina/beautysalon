package by.epam.silina.controller.listener;

import by.epam.silina.pool.ConnectionPool;
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
        log.info("++++++ contextInitialized : {}", servletContextEvent.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        ConnectionPool.getInstance().destroyPool();
        log.info("---------- contextDestroyed : {}", servletContextEvent.getServletContext().getContextPath());
    }
}
