package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static by.silina.beautysalon.controller.command.AttributeAndParameterName.*;

/**
 * The ChangeLocaleCommand class for change locale command.
 *
 * @author Silina Katsiaryna
 */
public class ChangeLocaleCommand implements Command {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Executes  change locale command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     * @throws CommandException if a command exception occurs.
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) throws CommandException {
        var sessionLocale = (String) sessionRequestContent.getSessionAttributeByName(LOCALE);
        var requestLocale = sessionRequestContent.getParameterByName(LOCALE);

        if (sessionLocale.equals(requestLocale)) {
            log.info("Current locale is the same that picked.");
        } else {
            switch (requestLocale) {
                case LOCALE_RU -> sessionRequestContent.putSessionAttribute(LOCALE, LOCALE_RU);
                case LOCALE_EN_US -> sessionRequestContent.putSessionAttribute(LOCALE, LOCALE_EN_US);
                default -> log.warn("Request parameter \"{}\" is not correct. Locale is not changed.", LOCALE);
            }
        }
        var page = (String) sessionRequestContent.getSessionAttributeByName(CURRENT_PAGE);
        return new Router(page, Router.Type.FORWARD);
    }
}
