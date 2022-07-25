package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;

import static by.silina.beautysalon.controller.command.PagePath.INDEX;

/**
 * The DefaultCommand class default command.
 *
 * @author Silina Katsiaryna
 */
public class DefaultCommand implements Command {

    /**
     * Executes change discount command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, type constant(FORWARD).
     */
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) {
        return new Router(INDEX, Router.Type.FORWARD);
    }
}
