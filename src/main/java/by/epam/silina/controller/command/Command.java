package by.epam.silina.controller.command;

import by.epam.silina.controller.SessionRequestContent;
import by.epam.silina.exception.CommandException;

@FunctionalInterface
public interface Command {
    Router execute(SessionRequestContent sessionRequestContent) throws CommandException;
}
