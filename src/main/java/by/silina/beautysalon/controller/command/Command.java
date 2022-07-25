package by.silina.beautysalon.controller.command;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.exception.CommandException;

/**
 * The Command interface.
 *
 * @author Silina Katsiaryna
 */
@FunctionalInterface
public interface Command {

    /**
     * Executes command.
     *
     * @param sessionRequestContent SessionRequestContent. The session and request content.
     * @return Router. The class contains page, jsonElement, type constant(FORWARD, REDIRECT, JSON).
     * @throws CommandException if a command exception occurs.
     */
    Router execute(SessionRequestContent sessionRequestContent) throws CommandException;
}
