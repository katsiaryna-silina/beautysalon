package by.epam.silina.controller.command.impl;

import by.epam.silina.controller.SessionRequestContent;
import by.epam.silina.controller.command.Command;
import by.epam.silina.controller.command.Router;

import static by.epam.silina.controller.command.PagePath.INDEX;

public class DefaultCommand implements Command {
    @Override
    public Router execute(SessionRequestContent sessionRequestContent) {
        //todo change "index.jsp" on smth or add constant
        return new Router(INDEX, Router.Type.FORWARD);
    }
}
