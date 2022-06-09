package by.epam.silina.controller.command.impl;

import by.epam.silina.controller.SessionRequestContent;
import by.epam.silina.controller.command.Command;
import by.epam.silina.controller.command.Router;

import static by.epam.silina.controller.command.PagePath.INDEX;

public class LogoutCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) {
        sessionRequestContent.invalidateSession();
        return new Router(INDEX, Router.Type.REDIRECT);
    }
}
