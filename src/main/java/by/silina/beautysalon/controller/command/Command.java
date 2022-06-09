package by.silina.beautysalon.controller.command;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.exception.CommandException;

@FunctionalInterface
public interface Command {
    Router execute(SessionRequestContent sessionRequestContent) throws CommandException;
}
