package by.silina.beautysalon.controller.command.impl;

import by.silina.beautysalon.controller.SessionRequestContent;
import by.silina.beautysalon.controller.command.Command;
import by.silina.beautysalon.controller.command.Router;

import static by.silina.beautysalon.controller.command.PagePath.INDEX;

public class DefaultCommand implements Command {
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) {
        return new Router(INDEX, Router.Type.FORWARD);
    }
}
