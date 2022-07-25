package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.PagePath;
import by.silina.beautysalon.controller.command.Router;

/**
 * The LogoutCommand class for logout command.
 *
 * @author Silina Katsiaryna
 */
public class LogoutCommand implements Command {

    /**
     * Executes logout command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(REDIRECT).
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) {
        sessionRequestContent.invalidateSession();
        return new Router(PagePath.INDEX, Router.Type.REDIRECT);
    }
}
