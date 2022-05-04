package by.epam.silina.command;

import by.epam.silina.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;

    default void refresh() {
    }
}
