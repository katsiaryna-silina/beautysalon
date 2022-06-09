package by.silina.beautysalon.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        //todo delete
        /* This method is called when an attribute is added to a session. */
        log.info("++++<<<--------> attributeAdded : {}", httpSessionBindingEvent.getSession().getAttribute("user_name"));
        log.info("++++<<<--------> attributeAdded : {}", httpSessionBindingEvent.getSession().getAttribute("current_page"));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        /* This method is called when an attribute is replaced in a session. */
        log.info(">><<<<--------> attributeReplaced : {}", httpSessionBindingEvent.getSession().getAttribute("user_name"));
        log.info(">><<<<--------> attributeReplaced : {}", httpSessionBindingEvent.getSession().getAttribute("current_page"));
    }
}
