package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;
import by.silina.beautysalon.controller.command.PagePath;

public class LogoutCommand implements Command {

    @Override
    public Router execute(SessionRequestContent sessionRequestContent) {
        sessionRequestContent.invalidateSession();
        return new Router(PagePath.INDEX, Router.Type.REDIRECT);
    }
}
